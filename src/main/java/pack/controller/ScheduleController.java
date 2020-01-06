package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.model.Film;
import pack.service.FilmService;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/schedule")
    public String schedule(@RequestParam(name = "film", required = false) Film film, Model model){
        List<Film> movies = filmService.findAll();
        movies.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        model.addAttribute("movies",movies);

        model.addAttribute("film",film);
        return "schedule";
    }

    @PostMapping("/selectFilm")
    public String selectFilm(RedirectAttributes redirectAttributes,  @RequestParam("combo") Film film, Model model){
        redirectAttributes.addAttribute("film",film);
        return "redirect:/schedule";
    }
}
