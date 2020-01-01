package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {
}
