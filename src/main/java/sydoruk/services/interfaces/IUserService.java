package sydoruk.services.interfaces;

import sydoruk.domain.User;
import sydoruk.domain.plainObjects.UserPojo;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);
    UserPojo getUser(Long id);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(User user, Long id);
    String deleteUser(Long id);

}
