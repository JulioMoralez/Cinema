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

    public List<Message> readAll() {
        return messageRepo.findAll();
    }

    public void save(Message text){
        messageRepo.save(text);
    }
}
