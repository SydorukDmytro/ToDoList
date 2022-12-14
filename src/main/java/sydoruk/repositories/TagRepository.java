package sydoruk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sydoruk.domain.Tag;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    List<Tag> findByName(String name);
}
