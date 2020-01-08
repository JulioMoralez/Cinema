package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.User;
import pack.service.OrderService;
import pack.service.UserService;

import java.util.Map;

import static pack.config.Consts.ROLES;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{user}")
    public String userEdit(@AuthenticationPrincipal User authUser, @PathVariable User user, Model model){
        if ((user==null) || (!userService.isAdmin(authUser))){
            user = userService.read(authUser.getId());
        }
        model.addAttribute("user",user);
        model.addAttribute("orders",orderService.findByUser(user));
        model.addAttribute("roles",ROLES);
        return "userEdit";
    }

    @GetMapping("/userEdit")
    public String userEditRedirect(@AuthenticationPrincipal User authUser, Model model){
        if (userService.isAdmin(authUser)){
            return "redirect:/admin";
        }
        else {
            model.addAttribute("user", userService.read(authUser.getId()));
            model.addAttribute("roles",ROLES);
            return "userEdit";
        }
    }

    @PostMapping("/userEdit")
    public String userEdit(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("id") User user,
            Model model){
        user.setUsername(username);
        user.setRoles(userService.getRoles(form));

        if (!userService.updateUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
        }
        else {
            model.addAttribute("usernameInfo", "Данные обновлены");
        }
        model.addAttribute("user",user);
        model.addAttribute("roles",ROLES);
        return "userEdit";
    }

}
