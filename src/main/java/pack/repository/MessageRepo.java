package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pack.model.Film;
import pack.model.Message;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findAll();
}
