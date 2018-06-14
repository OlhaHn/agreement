package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.UserEntity;

import java.util.List;


/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 6/10/18
 **/
public interface UserDao extends AbstructDao<UserEntity> {

    List<UserEntity> getUsers();
}
