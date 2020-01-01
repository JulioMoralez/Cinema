package pack.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pack.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("PersonRepoImpl")
public class PersonRepoImpl<T> implements Repo<T> {

    static final String ADD_PERSON= "INSERT INTO person (login,pass) VALUES (?,?);";
    static final String READ_PERSON= "SELECT * FROM person WHERE id=?;";
    static final String DELETE_PERSON= "DELETE FROM person WHERE id=?;";
    static final String UPDATE_PERSON= "UPDATE person SET login=?, pass=? WHERE id=?;";
    static final String READ_ALL_PERSONS= "SELECT * FROM person;";

    @Autowired
    private Connection connection;

    @Override
    public int save(T t) {
        Person person = (Person)t;
        try(PreparedStatement statement = connection.prepareStatement(ADD_PERSON)) {
            statement.setString(1,person.getLogin());
            statement.setString(2,person.getPass());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public T read(int id) {
        try(PreparedStatement statement = connection.prepareStatement(READ_PERSON)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return (T)(new Person(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(T t) {
        Person person = (Person)t;
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON)) {
            statement.setString(1,person.getLogin());
            statement.setString(2,person.getPass());
            statement.setInt(3,person.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)) {
            statement.setLong(1,id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<T> readAll() {
        List<T> persons = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(READ_ALL_PERSONS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                persons.add((T)(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3))));
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
