package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Order;
import pack.model.User;
import pack.repository.OrderRepo;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Order findById(Integer id){
        return orderRepo.findById(id).get();
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order save(Order film){
        return orderRepo.save(film);
    }

    public Order delete(Integer id) {
        if (orderRepo.findById(id).isPresent()) {
            try {
                orderRepo.deleteById(id);
            }
            catch (Exception e){
                return new Order(-1);
            }
        }
        return new Order(id);
    }

    public List<Order> findByUser(User user){
        return orderRepo.findByUser(user);
    }
}
