package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.dto.OrderConfirmDto;
import pack.model.Order;
import pack.model.Place;
import pack.model.Schedule;
import pack.model.User;
import pack.service.OrderService;
import pack.service.PlaceService;
import pack.service.ScheduleService;
import pack.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
public class OrderRest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

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


    //============================
    @RequestMapping(value = URL + "/confirm", method = RequestMethod.POST, consumes = "application/json")
    public OrderConfirmDto placeSelect(@RequestBody OrderConfirmDto orderConfirmDto){
        System.out.println(orderConfirmDto);

        int authUser = orderConfirmDto.getUserId();
        Schedule schedule = scheduleService.findById(orderConfirmDto.getScheduleId());

        int ticketCount=0;
        List<Place> places = placeService.findByScheduleAndStatus(schedule, authUser);
        for (Place place:places) {
            if (place.getStatus().equals(authUser)){
                ticketCount++;
            }
        }
        if (ticketCount==0){        // если нет выбранных билетов, то ничего не делаем
            return orderConfirmDto;
        }

        Order order = new Order();

        String key = UUID.randomUUID().toString().substring(0,6).toUpperCase();
        List<String> keys = orderService.findAll().stream().map(Order::getKey).collect(Collectors.toList());

        int i=0;
        while ((keys.contains(key)) && (i<10)){
            i++;
            key = UUID.randomUUID().toString().substring(0,6).toUpperCase();
        }
        if (i==10){
            System.out.println("Error Key");
            return orderConfirmDto;
        }

        order.setSchedule(schedule);
        order.setKey(key);
        if (schedule.getPrice()!=null){
            order.setPrice(schedule.getPrice()*ticketCount);
        }
        else {
            order.setPrice(0);
        }
        order.setOrderTime(LocalDateTime.now());
        order.setUser(userService.findById(authUser));
        order = orderService.save(order);

        for (Place place:places){
            place.setStatus(0);
            place.setBlockTime(null);
            place.setOrder(order);
            placeService.save(place);
        }
        return orderConfirmDto;
    }

    @RequestMapping(value = URL  + "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<Order> findByUser(@PathVariable Integer userId){
        return orderService.findByUser(new User(userId));
    }

}

