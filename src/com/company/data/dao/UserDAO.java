package com.company.data.dao;

import com.company.data.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {

    User user = new User();
    String userMaxID = "SELECT max(id) FROM user_tbl";
    String saveSQl = "INSERT INTO user_tbl (id, name, password, email, birthday, salary) VALUES (?,?,?,?,?,?)";
    String updateSQl = "UPDATE user_tbl Set name=?, password=?, email=?, birthday=?, salary=? WHERE ID=?";
    String deleteSQl = "DELETE FROM  user_tbl WHERE ID=?";
    String countSQl = "SELECT COUNT(*) FROM user_tbl";
    String findAllSQl = "SELECT * FROM user_tbl";
    String loginSQl = "SELECT * FROM user_tbl WHERE name=? and password=?";
    String changePasswordSQl = "UPDATE user_tbl Set password=? WHERE name=? and Password=?";
    Long currentID;

    public UserDAO() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userMaxID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                currentID = resultSet.getLong(1);
            } else {
                currentID = 0L;
            }
            connection.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public Object save(Object o) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
        currentID++;
        user.setId(currentID);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setDate(5, new Date(user.getBirthday().getTime()));
        preparedStatement.setDouble(6, user.getSalary());
        preparedStatement.executeUpdate();
        connection.commit();
        return user;
    }

    @Override
    public Object update(Object o) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQl);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
        preparedStatement.setDouble(5, user.getSalary());
        preparedStatement.executeUpdate();
        connection.commit();
        return user;
    }

    @Override
    public Object delete(Object o) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQl);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        return user;
    }

    @Override
    public int count() throws SQLException {
        Long count = 0L;
        PreparedStatement preparedStatement = connection.prepareStatement(countSQl);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) count = resultSet.getLong(1);
        connection.commit();
        return count.intValue();
    }

    @Override
    public List findAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findAllSQl);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            java.util.Date birthday = resultSet.getDate("birthday");
            Double salary = resultSet.getDouble("salary");
            users.add(new User(id, name, email, password, birthday, salary));
        }
        connection.commit();
        return users;
    }

    public boolean login(String name, String password) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(loginSQl);
//        preparedStatement.setString(1, name);
//        preparedStatement.setString(2, password);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        boolean result = resultSet.next();
//        connection.commit();
//        return result;


        boolean result = false;
        PreparedStatement ps = connection.prepareStatement(loginSQl);
        ps.setString(1, name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }
        connection.commit();
        return result;

    }

    public int changePassword(String user, String currentPassword, String newPassword) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSQl);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setString(2, user);
        preparedStatement.setString(3, currentPassword);
        int result = preparedStatement.executeUpdate();
        connection.commit();
        return result;

    }

}
