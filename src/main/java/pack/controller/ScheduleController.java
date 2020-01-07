package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.model.Film;
import pack.model.Hall;
import pack.model.Schedule;
import pack.service.FilmService;
import pack.service.HallService;
import pack.service.ScheduleService;
import pack.service.UtilService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Controller
public class ScheduleController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HallService hallService;

    @Autowired
    private UtilService utilService;

    private static final int MAX_BEST=2;
    private List<Film> moviesToday=new ArrayList<>();
    private List<Film> moviesTodayBest=new ArrayList<>();

    @GetMapping("/schedule")
    public String schedule(@RequestParam(name = "film", required = false) Film film,
                           @RequestParam(name = "hall", required = false) Hall hall,
                           @RequestParam(name = "day", required = false) Integer day,
                            Model model){
        List<Film> movies = filmService.findAll();
        List<Hall> halls = hallService.findAll();

        movies.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        halls.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        model.addAttribute("movies",movies);
        model.addAttribute("halls",halls);
        model.addAttribute("hours",scheduleService.getHours());
        model.addAttribute("mins",scheduleService.getMins());

        model.addAttribute("days",utilService.getDaysList());

        if (day==null) {
            day=LocalDate.now().getDayOfWeek().getValue();
        }

        moviesToday=filmService.findByDay(day);

        moviesTodayBest.clear();
        int i=0;
        int size = moviesToday.size();
        while ((i<MAX_BEST) && (i<size)){
            moviesTodayBest.add(moviesToday.get(i));
            i++;
        }
        model.addAttribute("moviesToday",moviesToday);
        model.addAttribute("moviesTodayBest",moviesTodayBest);

        model.addAttribute("schedules",scheduleService.findByDay(day));

//            model.addAttribute("schedules",scheduleService.findAll());

        model.addAttribute("film",film);
        return "schedule";
    }



    @PostMapping("/addSchedule")
    public String addSchedule(RedirectAttributes redirectAttributes,
                             @RequestParam("filmCombo") Film film,
                             @RequestParam("hallCombo") Hall hall,
                             @RequestParam("daysCombo") Integer day,
                             @RequestParam("hoursCombo") Integer hour,
                             @RequestParam("minsCombo") Integer min){
        Schedule schedule = new Schedule();
        schedule.setFilm(film);
        schedule.setHall(hall);
        schedule.setDay(day);
        schedule.setTime(hour*60+min);
        scheduleService.save(schedule);
        redirectAttributes.addAttribute("film",film);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/hall/{hall}")
    public String scheduleSelectHall(RedirectAttributes redirectAttributes, @PathVariable Hall hall){
        redirectAttributes.addAttribute("hall",hall);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/day/{day}")
    public String scheduleSelectDay(RedirectAttributes redirectAttributes, @PathVariable Integer day){
        redirectAttributes.addAttribute("day",day);
        return "redirect:/schedule";
    }





}
