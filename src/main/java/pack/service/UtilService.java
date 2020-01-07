package pack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class UtilService {

    @Value("${upload.path}")
    private String rootPicPath;

    private Locale locale = new Locale("ru");

    public UtilService() {
    }


    public String generatePicPath(MultipartFile picPath){
        if (picPath!=null && !picPath.getOriginalFilename().isEmpty()){
            File uploadDir = new File(rootPicPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultPicPath = UUID.randomUUID().toString()+"."+picPath.getOriginalFilename();
            try {
                picPath.transferTo(new File(uploadDir + "/" + resultPicPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultPicPath;
        }
        return null;
    }

    private List<MyDay> daysList = new ArrayList<>();
    private int currentYear=-1;
    private int currentDay=-1;

    public List<MyDay> getDaysList() {
        LocalDate localDate = LocalDate.now();
        if (currentYear!=localDate.getYear() || currentDay!=localDate.getDayOfYear()){
            currentYear=localDate.getYear();
            currentDay=localDate.getDayOfYear();
            daysList.clear();
            for (int i = 1; i <=7; i++) {
                daysList.add(new MyDay(localDate.getDayOfWeek().getValue(),
                        localDate.getDayOfMonth(),
                        currentYear,
                        localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale),
                        localDate.getMonth().getDisplayName(TextStyle.FULL, locale)));
                localDate = localDate.plusDays(1);
            }
        }
        return daysList;
    }

    public static class MyDay{
        private int dayOfWeek;
        private int dayOfMonth;
        private int year;
        private String dayOfWeekName;
        private String monthName;

        public MyDay(int dayOfWeek, int dayOfMonth, int year, String dayOfWeekName, String monthName) {
            this.dayOfWeek = dayOfWeek;
            this.dayOfMonth = dayOfMonth;
            this.year = year;
            this.dayOfWeekName = dayOfWeekName;
            this.monthName = monthName;
        }

        public int getDayOfWeek() {
            return dayOfWeek;
        }

        public int getDayOfMonth() {
            return dayOfMonth;
        }

        public int getYear() {
            return year;
        }

        public String getDayOfWeekName() {
            return dayOfWeekName;
        }

        public String getMonthName() {
            return monthName;
        }
    }
}
