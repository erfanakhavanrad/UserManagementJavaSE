package com.company.dao;

import com.company.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO {

    String saveSQl = "INSERT INTO user_tb1 (id, name, password, email, birthday, salary) VALUES (?,?,?,?,?,?)";

    @Override
    public Object save(Object o) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
//        preparedStatement.setInt();
    }

    @Override
    public Object update(Object o) throws SQLException {
        return null;
    }

    @Override
    public Object delete(Object o) throws SQLException {
        return null;
    }

    @Override
    public int count() throws SQLException {
        return 0;
    }

    @Override
    public List findAll() throws SQLException {
        return null;
    }
}
