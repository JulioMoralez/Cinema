package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pack.model.Message;
import pack.model.User;
import pack.repository.MessageRepo;
import pack.service.UserService;
import pack.service.UtilService;

import java.util.List;


public class NewsController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilService utilService;

    @GetMapping("/news")
    String news(@AuthenticationPrincipal User user, Model model){
        List<Message> messages = (List<Message>) messageRepo.findAll();
        if (messages.size()>0){
            model.addAttribute("messages",messages);
        }
        if (user!=null){
            model.addAttribute("username", user.getUsername());
           if (userService.isModerator(user)){
               model.addAttribute("moderator","y");
            }
        }
        return "news";
    }

    @PostMapping("/news")
    String addNews(@AuthenticationPrincipal User user,
                   @RequestParam MultipartFile picPath,
                   @RequestParam String text){
        Message message = new Message(text,user);
        message.setPicPath(utilService.generatePicPath(picPath));
        messageRepo.save(message);
        return "redirect:/news";
    }

}
