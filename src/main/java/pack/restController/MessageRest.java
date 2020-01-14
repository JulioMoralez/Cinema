package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Message;
import pack.service.MessageService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageRest {

    @Autowired
    private MessageService messageService;

    static final String URL = "/message";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Message find(@PathVariable Message entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Message save(@RequestBody Message entity){
        return messageService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Message delete(@PathVariable Integer id){
        return messageService.delete(id);
    }
}

