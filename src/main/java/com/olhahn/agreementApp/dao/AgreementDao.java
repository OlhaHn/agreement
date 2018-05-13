package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.AgreementEntity;
import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Interface for dao layer (agreement table).
 */
public interface AgreementDao extends AbstructDao<AgreementEntity> {

    /**
     * Function to get active agreements.
     * @return list of active agreements
     */
    List<AgreementEntity> getActive();

    /**
     * Function to get nonactive agreements.
     * @return list of passive agreements
     */
    List<AgreementEntity> getPassive();
}
