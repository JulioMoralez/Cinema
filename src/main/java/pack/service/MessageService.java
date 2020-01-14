package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Message;
import pack.repository.MessageRepo;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public Message findById(Integer id){
        return messageRepo.findById(id).get();
    }

    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    public Message save(Message film){
        return messageRepo.save(film);
    }

    public Message delete(Integer id) {
        if (messageRepo.findById(id).isPresent()) {
            try {
                messageRepo.deleteById(id);
            }
            catch (Exception e){
                return new Message(-1);
            }
        }
        return new Message(id);
    }
}
