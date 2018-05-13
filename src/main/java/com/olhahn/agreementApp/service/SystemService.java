package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.SystemEntity;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Service for interaction with system table.
 */
public interface SystemService extends AbstructService<SystemEntity> {

    /**
     * Function to find system having it's name.
     * @param name - name of the system to find.
     * @return system with name from argument
     */
    SystemEntity getSystemByName(String name);
}
