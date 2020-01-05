package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Message;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {
}
