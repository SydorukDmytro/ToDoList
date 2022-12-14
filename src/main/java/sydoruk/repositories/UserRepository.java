package sydoruk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sydoruk.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
}
