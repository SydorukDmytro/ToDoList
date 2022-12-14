package sydoruk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sydoruk.domain.User;
import sydoruk.domain.plainObjects.UserPojo;
import sydoruk.repositories.UserRepository;
import sydoruk.services.interfaces.IUserService;
import sydoruk.utils.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final Converter converter;

    @Autowired
    public UserService(Converter converter, UserRepository userRepository) {
        this.converter = converter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserPojo createUser(User user) {

        userRepository.save(user);

        return converter.userToPojo(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserPojo getUser(Long id) {

        Optional<User> foundUserOpt = userRepository.findById(id);

        if(foundUserOpt.isPresent()){
            return converter.userToPojo(foundUserOpt.get());
        }else{
            return converter.userToPojo(new User());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPojo> getAllUsers() {
        List<User> listOfUsers= userRepository.findAll();

        return listOfUsers.stream().map(user -> converter.userToPojo(user)).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public UserPojo updateUser(User source, Long id) {
        Optional<User> userForUpdate = userRepository.findById(id);
        if (userForUpdate.isPresent()) {
            User target = userForUpdate.get();
            target.setEmail(source.getEmail());
            target.setPassword(source.getPassword());
            userRepository.save(target);
            return converter.userToPojo(target);
        } else {
            return converter.userToPojo(new User());
        }
    }

    @Override
    @Transactional
    public String deleteUser(Long id) {
        Optional<User> userForDelete = userRepository.findById(id);
        if (userForDelete.isPresent()){
            userRepository.delete(userForDelete.get());
            return "User with id: " + id + " was successfully deleted";
        }else{
            return "User with id: " + id + " doesn't exist";
        }
    }
}
