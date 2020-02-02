package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pack.dto.UserDto;
import pack.model.User;
import pack.service.MailService;
import pack.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public User validateLogin(@AuthenticationPrincipal User authUser) {
        return authUser;
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET, produces = "application/json")
    public UserDto findUser(@PathVariable User user){
        if (user!=null){
            return new UserDto(user);
        }
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: userService.findAll()){
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    public UserDto saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST, consumes = "application/json")
    public boolean updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @RequestMapping(value = "/user/sendemail", method = RequestMethod.POST, consumes = "application/json")
    public UserDto sendEmail(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        if (user!=null) {
            Random r = new Random();
            user.setVerifyCode(Integer.valueOf(r.nextInt(10000)).toString());
            mailService.sendSimpleEmail(user.getEmail(), "Код подтверждения", "Код для " + user.getUsername() + " : " + user.getVerifyCode());
            userService.updateUser(user);
            return userDto;
        }
        return new UserDto(-1);
    }

    @RequestMapping(value = "/user/confirm", method = RequestMethod.POST, consumes = "application/json")
    public UserDto confirmCode(@RequestBody UserDto userDto){
        User user = userService.findById(userDto.getId());
        if (user!=null){
            if (user.getVerifyCode().trim().equals(userDto.getVerifyCode())){
                user.setEmailConfirmed(true);
                userService.updateUser(user);
                userDto.setEmailConfirmed(true);
                return userDto;
                }
            }
        return new UserDto(-1);
    }

}

