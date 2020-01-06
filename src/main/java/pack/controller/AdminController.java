package pack.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.Role;
import pack.model.User;
import pack.service.UserService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static pack.config.Consts.ROLES;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userService.readAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Integer id,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/user/{user}")
    public String userEdit(@AuthenticationPrincipal User authUser, @PathVariable User user, Model model){
            if ((user!=null) && (userService.isAdmin(authUser))){
                model.addAttribute("user",user);
            }
            else {
                model.addAttribute("user",userService.read(authUser.getId()));
            }
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
