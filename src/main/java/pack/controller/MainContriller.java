package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pack.model.Person;

@Controller
public class MainContriller {

    @GetMapping("/")
    String add(){
        return "index";
    }

}
