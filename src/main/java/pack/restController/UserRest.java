package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pack.dto.UserDto;
import pack.model.User;
import pack.service.UserService;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

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
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        userService.updateUser(userDto);

    }
}

