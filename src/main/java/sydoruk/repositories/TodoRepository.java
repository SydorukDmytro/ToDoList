package sydoruk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sydoruk.domain.Todo;
import sydoruk.domain.User;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
