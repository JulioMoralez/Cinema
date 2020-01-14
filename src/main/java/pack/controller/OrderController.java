package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.*;
import pack.service.OrderService;
import pack.service.PlaceService;
import pack.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/order/{order}")
    public String order(@PathVariable("order") Order order, Model model){
        model.addAttribute("order",order);
        return "order";
    }

    @PostMapping("/order")
    public String saveOrder(@AuthenticationPrincipal User authUser,
                            @RequestParam("schedule") Schedule schedule){

        int ticketCount=0;
        List<Place> places = placeService.findByScheduleAndStatus(schedule,authUser.getId());
        for (Place place:places) {
            if (place.getStatus().equals(authUser.getId())){
                ticketCount++;
            }
        }
        if (ticketCount==0){        // если нет выбранных билетов, то ничего не делаем
            return "redirect:/hall/"+schedule.getId();
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
            return "order";
        }

        order.setSchedule(schedule);
        order.setKey(key);
        order.setPrice(schedule.getPrice()*ticketCount);
        order.setOrderTime(LocalDateTime.now());
        order.setUser(authUser);
        order = orderService.save(order);

        for (Place place:places){
            place.setStatus(0);
            place.setBlockTime(null);
            place.setOrder(order);
            placeService.save(place);
        }
        return "redirect:/order/"+order.getId();
    }
}
