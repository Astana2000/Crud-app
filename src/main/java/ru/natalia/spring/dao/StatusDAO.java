package ru.natalia.spring.dao;

import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatusDAO {

    private static int PEOPLE_COUNT = 0;
    private static final String URL = "jdbc:postgresql://localhost:5432/nir_spring";
    private static final String USERNAME = "postgres";

    private static Connection connection;

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Status> index() {
        List<Status> statuses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Status";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statuses;
    }

    public Status show(int id) {
        Status status = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("Select * from Status where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            status = new Status();
            status.setId(resultSet.getInt("id"));
            status.setName(resultSet.getString("name"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    public void save(Status status)  {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Status VALUES(1, ?)");
            preparedStatement.setString(1,status.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Status updateStatus) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Status SET name=? where id=?");
            preparedStatement.setString(1,updateStatus.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("Delete from Status where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
