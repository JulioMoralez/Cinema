package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pack.dto.UserDto;
import pack.model.Film;
import pack.model.User;
import pack.service.FilmService;
import pack.service.UserService;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FilmRest {

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/film/{film}", method = RequestMethod.GET, produces = "application/json")
    public Film findFilm(@PathVariable Film film){
        System.out.println("123456");
        return film;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = "application/json")
    public List<Film> findAll() {
        return filmService.findAll();
    }

//    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
//    public UserDto saveUser(@RequestBody UserDto userDto){
//        return filmService.saveUser(userDto);
//    }
//
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST, consumes = "application/json")
//    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
//        filmService.updateUser(userDto);
//
//    }
}

