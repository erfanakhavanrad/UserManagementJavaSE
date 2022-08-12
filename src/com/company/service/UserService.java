package com.company.service;

import com.company.data.dao.UserDAO;
import com.company.data.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserService {
    UserDAO userDAO = new UserDAO();
    static Logger logger = Logger.getLogger(UserService.class.getName());

    public User save(User user) {
        try {
            return userDAO.save(user);
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public User update(User user) {
        try {
            return userDAO.update(user);
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public User delete(User user) {
        try {
            if (!user.getEmail().matches("^(.+)@(.+)$"))
                throw new ServiceException("Email validation error: " + user.getEmail());
            user = userDAO.delete(user);
            userDAO.commit();
            return user;
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public int count() {
        try {
            return userDAO.count();
        } catch (SQLException sqlException) {
            throw new ServiceException(sqlException);
        }
    }

    public List<User> findAll() {
        try {
            return userDAO.findAll();
        } catch (SQLException sqlException) {
            throw new ServiceException(sqlException);
        }
    }


}
