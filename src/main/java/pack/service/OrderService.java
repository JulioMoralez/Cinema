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

    public Order save(Order order){
        return orderRepo.save(order);
    }

    public List<Order> findAll(){
        return orderRepo.findAll();
    }

    public List<Order> findByUser(User user){
        return orderRepo.findByUser(user);
    }
}
