package pack.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.model.Schedule;
import pack.service.ScheduleService;
import pack.service.UtilService;

import java.util.List;


@CrossOrigin
@RestController
public class UtilRest {

    @Autowired
    private UtilService utilService;


    static final String URL = "/util";


    //==========================

    @RequestMapping(value = URL + "/days", method = RequestMethod.GET, produces = "application/json")
    public List<UtilService.MyDay> findByDateWeek() {
        return utilService.getDaysList();
    }
}

