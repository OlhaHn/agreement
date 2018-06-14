package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.dao.UserDao;
import com.olhahn.agreementApp.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 6/10/18
 **/
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Getter for userDao
     * @return userDao
     */
    public UserDao getUserDao() {
        return this.userDao;
    }

    /**
     * Setter for userDao field
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<UserEntity> getAll() {
        return this.userDao.getAll();
    }

    public boolean removeObject(int id) {
        return this.userDao.removeObject(id);
    }

    public UserEntity findObjectById(int id) {
        return this.userDao.findObjectById(id);
    }

    public boolean updateObject(UserEntity input) {
        return this.userDao.updateObject(input);
    }

    public boolean addObject(UserEntity input) {
        return this.userDao.addObject(input);
    }

    public List<UserEntity> getUsers() {
        return this.userDao.getUsers();
    }
}
