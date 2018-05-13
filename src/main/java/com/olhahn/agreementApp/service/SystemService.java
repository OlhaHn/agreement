package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.SystemEntity;

/**
 * Service for interaction with system table.
 */
public interface SystemService extends AbstructService<SystemEntity> {

    SystemEntity getSystemByName(String name);

}
