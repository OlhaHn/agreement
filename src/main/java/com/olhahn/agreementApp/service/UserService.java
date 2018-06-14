package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 6/10/18
 **/
public interface UserService extends AbstructService<UserEntity>{

    List<UserEntity> getUsers();

}
