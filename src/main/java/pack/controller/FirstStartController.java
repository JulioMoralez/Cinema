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
    private static final String FIND_ADMIN= "SELECT * FROM t_user WHERE username=?;";


    @GetMapping("/firststart")
    public String firstStart(){
        createRole();
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
}
