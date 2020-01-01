package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.model.Film;
import pack.model.Person;
import pack.repository.Repo;

@Controller
public class HelloController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    @Qualifier("PersonRepoImpl")
    Repo<Person> personRepo;

    @Autowired
    @Qualifier("FilmRepoImpl")
    Repo<Film> filmRepo;


    @GetMapping("/add")
    String add(){
        Person person = new Person();
        person.setLogin("Sam");
        person.setPass("1234");
        personRepo.save(person);
        return "hello";
    }

    @GetMapping("/update/{id}")
    String sayHello(@PathVariable("id") int id) {
        Person person=personRepo.read(id);
        if (person!=null){
            person.setLogin("Jane");
            person.setPass("qw1");
            System.out.println(personRepo.update(person));
        }
        return "hello";
    }

    @GetMapping("/hello/{id}")
    String sayHello(@PathVariable("id") int id, Model model) {
        model.addAttribute("message","ololo");
        Person person = new Person();
        person.setLogin("Sam");
        person.setPass("1234");

//        person = personRepo.read(id);
//        if (person!=null){
//            System.out.println(person);
//        }else {
//            System.out.println("no person");
//        }

//        List<Person> people = personRepo.readAll();
//        people.forEach(System.out::println);

        System.out.println(personRepo.delete(id));

        return "hello";
    }


//        Connection connection=null;
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS person (ID serial PRIMARY KEY, name   varchar(40));");
//            statement.execute("INSERT INTO person (name) VALUES ('Sam');");
//            statement.execute("INSERT INTO person (name) VALUES ('Max');");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (connection!=null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        persons.forEach(System.out::println);
//
//        model.addAttribute("message","hello");
//        model.addAttribute("persons",persons);
//
//
//        return "index";
//    }
}
