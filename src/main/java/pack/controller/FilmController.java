package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pack.model.Film;
import pack.model.Genre;
import pack.model.Role;
import pack.model.User;
import pack.repository.FilmRepo;
import pack.repository.GenreRepo;
import pack.service.FilmService;
import pack.service.GenreService;
import pack.service.UtilService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static pack.config.Consts.ROLES;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;
    
    @Autowired
    private GenreService genreService;

    @Autowired
    private UtilService utilService;

    @GetMapping("/film")
    String film(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("genres",genreService.findAll());
        model.addAttribute("movies",filmService.findAll());
        return "film";
    }

    @GetMapping("/film/delete/{id}")
    public String filmDelete(@AuthenticationPrincipal User authUser, @PathVariable Integer id, Model model){
        filmService.deleteFilm(id);
        return "redirect:/film";
    }

    @GetMapping("/film/{film}")
    public String filmForm(@AuthenticationPrincipal User authUser, @PathVariable Film film, Model model){
        model.addAttribute("film",film);
        return "filmForm";
    }

    @GetMapping("/film/edit/{id}")
    String filmEdit(@AuthenticationPrincipal User authUser, @PathVariable Integer id, Model model){
        model.addAttribute("movies",filmService.findAll());
        model.addAttribute("genres",genreService.findAll());
        model.addAttribute("film",filmService.findById(id));
        return "film";
    }

    @PostMapping("/filmEdit")
    String filmEdit(@AuthenticationPrincipal User authUser,
                    @RequestParam("id") Film film,
                    @RequestParam("picPath") MultipartFile picPath,
                    @RequestParam Map<String,String> form,
                    Model model){
        film.setGenres(filmService.getGenres(form));
        if (picPath!=null && !picPath.getOriginalFilename().isEmpty()){
            film.setPicPath(utilService.generatePicPath(picPath));
        }
        filmService.save(film);
        return "redirect:/film";
    }

    @PostMapping("/filmAdd")
    String filmAdd(@AuthenticationPrincipal User authUser,
                   @RequestParam("name") String name,
                   @RequestParam("year") Integer year,
                   @RequestParam("picPath") MultipartFile picPath,
                   @RequestParam Map<String,String> form,
                    Model model){
        Film film = new Film();
        film.setName(name);
        film.setYear(year);
        film.setRating(50);
        film.setPicPath(utilService.generatePicPath(picPath));

        film.setGenres(filmService.getGenres(form));

        filmService.save(film);
        return "redirect:/film";
    }


}
