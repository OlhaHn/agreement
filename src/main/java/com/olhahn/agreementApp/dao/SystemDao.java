package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.SystemEntity;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Interface for System DAO.
 */
public interface SystemDao extends AbstructDao<SystemEntity> {

    /**
     * Finds system by name.
     * @param name - name of the system
     * @return system
     */
    List<SystemEntity> getSystemByName(String name);
}
