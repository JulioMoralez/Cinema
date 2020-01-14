package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pack.model.User;
import pack.service.UserService;


public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User userForm, Model model) {
        if (userForm.getUsername().isEmpty()){
            model.addAttribute("usernameError", "Поле не заполнено");
            return "registration";
        }
        model.addAttribute("username", userForm.getUsername());
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (userForm.getPassword().isEmpty()){
            model.addAttribute("passwordError", "Поле не заполнено");
            return "registration";
        }
        if (userForm.getPassword().length()<6){
            model.addAttribute("passwordError", "Пароль меньше 6 символов");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }

}
