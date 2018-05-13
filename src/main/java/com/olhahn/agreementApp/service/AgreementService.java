package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.AgreementEntity;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Interface for interaction with agreement table in DB.
 */
public interface AgreementService extends AbstructService<AgreementEntity> {

    /**
     * Function to get list of active agreements.
     * @return list of active agreements
     */
    List<AgreementEntity> getActive();

    /**
     * Function to get list of passive agreements.
     * @return list of nonactive agreements
     */
    List<AgreementEntity> getNonactive();
}
