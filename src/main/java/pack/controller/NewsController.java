package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pack.model.Message;
import pack.model.Person;
import pack.model.Role;
import pack.model.User;
import pack.repository.MessageRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static pack.config.Consts.*;

@Controller
public class NewsController {

    @Value("${upload.path}")
    private String rootPicPath;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/news")
    String news(@AuthenticationPrincipal User user, Model model){
        List<Message> messages = messageRepo.findAll();
        if (messages.size()>0){
            model.addAttribute("messages",messages);
        }
        if (user!=null){
            model.addAttribute("username", user.getUsername());
           if ((user.getRoles().contains(ROLE_ADMIN)) || (user.getRoles().contains(ROLE_MODERATOR))){
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
        if (picPath!=null && !picPath.getOriginalFilename().isEmpty()){
            File uploadDir = new File(rootPicPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultPicPath = UUID.randomUUID().toString()+"."+picPath.getOriginalFilename();
            try {
                picPath.transferTo(new File(uploadDir + "/" + resultPicPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setPicPath(resultPicPath);
        }
        messageRepo.save(message);
        return "redirect:/news";
    }

}
