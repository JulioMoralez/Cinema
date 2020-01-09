package pack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;


@Controller
public class FirstStartController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private static final String ADD_ROLE= "INSERT INTO t_role (id,name) VALUES (?,?);";
    private static final String ADD_GENRE= "INSERT INTO t_genre (id,name) VALUES (?,?);";
    private static final String ADD_HALL= "INSERT INTO t_hall (id,name,place,row) VALUES (?,?,?,?);";


    @GetMapping("/firststart/role")
    public String firstStartRole(){
        createRole();
        return "index";
    }

    @GetMapping("/firststart/genre")
    public String firstStartGenre(){
        createGenre();
        return "index";
    }

    @GetMapping("/firststart/hall")
    public String firstStartHall(){
        createHall();
        return "index";
    }

    private void createRole() {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_ROLE)) {
                statement.setInt(1, 1);
                statement.setString(2, "ROLE_USER");
                statement.executeUpdate();
                statement.setInt(1, 2);
                statement.setString(2, "ROLE_ADMIN");
                statement.executeUpdate();
                statement.setInt(1, 3);
                statement.setString(2, "ROLE_MODERATOR");
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createGenre() {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_GENRE)) {
                statement.setInt(1, 1);
                statement.setString(2, "Фантастика");
                statement.executeUpdate();
                statement.setInt(1, 2);
                statement.setString(2, "Боевик");
                statement.executeUpdate();
                statement.setInt(1, 3);
                statement.setString(2, "Комедия");
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createHall() {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_HALL)) {
                statement.setInt(1, 1);
                statement.setString(2, "A");
                statement.setInt(3, 3);
                statement.setInt(4, 4);
                statement.executeUpdate();
                statement.setInt(1, 2);
                statement.setString(2, "B");
                statement.setInt(3, 5);
                statement.setInt(4, 5);
                statement.executeUpdate();
                statement.setInt(1, 3);
                statement.setString(2, "C");
                statement.setInt(3, 10);
                statement.setInt(4, 8);
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
