package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.model.Genre;
import pack.model.User;
import pack.repository.GenreRepo;
import pack.service.FilmService;

import java.util.List;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;
    
    @Autowired
    private GenreRepo genreRepo;

    @GetMapping("/film")
    String news(@AuthenticationPrincipal User user, Model model){
        List<Genre> genres = (List<Genre>) genreRepo.findAll();
//        Film film = new Film();
//        film.setId(5);
//        film.setName("Звездные войны");
//        film.setYear(1977);
//        film.setGenre(genres.stream().filter(f->f.getName().equals("Фантастика")).findFirst().get());
//        filmService.save(film);

        model.addAttribute("movies",filmService.findAll());

        return "film";
    }

    @GetMapping("/film/delete/{id}")
    public String filmDelete(@AuthenticationPrincipal User authUser, @PathVariable Integer id, Model model){
        filmService.deleteFilm(id);
        return "redirect:/film";
    }
}
