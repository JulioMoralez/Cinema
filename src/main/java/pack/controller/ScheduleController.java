package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack.model.Film;
import pack.model.Hall;
import pack.model.Schedule;
import pack.model.User;
import pack.service.FilmService;
import pack.service.HallService;
import pack.service.ScheduleService;
import pack.service.UtilService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/")
    public String schedule(@AuthenticationPrincipal User authUser,
                            @RequestParam(name = "film", required = false) Film film,
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

        LocalDate localDate = LocalDate.now();
        if (day!=null) {
            int d = (day-localDate.getDayOfWeek().getValue()+7)%7;
            localDate=localDate.plusDays(d);
        }

        moviesToday=filmService.findByDate(localDate);

        moviesTodayBest.clear();
        int i=0;
        int size = moviesToday.size();
        while ((i<MAX_BEST) && (i<size)){
            moviesTodayBest.add(moviesToday.get(i));
            i++;
        }
        model.addAttribute("moviesToday",moviesToday);
        model.addAttribute("moviesTodayBest",moviesTodayBest);

        model.addAttribute("schedules",scheduleService.findByDate(localDate));
        model.addAttribute("user", authUser);

//            model.addAttribute("schedules",scheduleService.findAll());

        model.addAttribute("film",film);
        return "index";
    }



    @PostMapping("/addSchedule")
    public String addSchedule(RedirectAttributes redirectAttributes,
                             @RequestParam("filmCombo") Film film,
                             @RequestParam("hallCombo") Hall hall,
                             @RequestParam("daysCombo") Integer day,
                             @RequestParam("hoursCombo") Integer hour,
                             @RequestParam("minsCombo") Integer min,
                             @RequestParam("price") String price){
        Schedule schedule = new Schedule();
        schedule.setFilm(film);
        schedule.setHall(hall);
        int p;
        try {
            p=Integer.parseInt(price);
        }
        catch (RuntimeException e){
            p=100;
        }
        schedule.setPrice(p);

        LocalDate localDate = LocalDate.now();
        int d = (day-localDate.getDayOfWeek().getValue()+7)%7;
        localDate=localDate.plusDays(d);
        schedule.setDate(localDate);
        schedule.setTime(hour*60+min);
        scheduleService.save(schedule);
        redirectAttributes.addAttribute("film",film);
        return "redirect:/";
    }

    @GetMapping("/schedule/hall/{hall}")
    public String scheduleSelectHall(RedirectAttributes redirectAttributes, @PathVariable Hall hall){
        redirectAttributes.addAttribute("hall",hall);
        return "redirect:/";
    }

    @GetMapping("/schedule/day/{day}")
    public String scheduleSelectDay(RedirectAttributes redirectAttributes, @PathVariable Integer day){
        redirectAttributes.addAttribute("day",day);
        return "redirect:/";
    }





}
