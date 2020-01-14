package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Order;
import pack.service.OrderService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderRest {

    @Autowired
    private OrderService orderService;

    static final String URL = "/order";

    @RequestMapping(value = URL + "/{entity}", method = RequestMethod.GET, produces = "application/json")
    public Order find(@PathVariable Order entity){
        return entity;
    }

    @RequestMapping(value = URL + "s", method = RequestMethod.GET, produces = "application/json")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, consumes = "application/json")
    public Order save(@RequestBody Order entity){
        return orderService.save(entity);
    }

    @RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Order delete(@PathVariable Integer id){
        return orderService.delete(id);
    }
}

