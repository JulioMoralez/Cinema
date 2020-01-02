package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pack.model.Person;
import pack.model.User;
import pack.service.MailService;

@Controller
public class MainContriller {

    @Autowired
    private MailService mailService;

    @GetMapping("/")
    String add(){
        return "index";
    }

    @GetMapping("/mail")
    public String sendMail(@AuthenticationPrincipal User authUser){
        mailService.sendSimpleEmail("akbp@mail.ru","sub","hello from "+authUser.getUsername());
        return "index";
    }

}
