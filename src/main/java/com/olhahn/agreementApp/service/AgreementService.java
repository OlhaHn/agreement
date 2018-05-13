package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.AgreementEntity;

import java.util.List;

/**
 * Interface for interaction with agreement table in DB.
 */
public interface AgreementService extends AbstructService<AgreementEntity> {

    /**
     *
     * @return list of active agreements
     */
    List<AgreementEntity> getActive();

    List<AgreementEntity> getNonactive();
}
