package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.dao.AgreementDao;
import com.olhahn.agreementApp.model.AgreementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Implementation of AgreementService.
 */
@Service
public class AgreementServiceImpl implements AgreementService {

    /**
     * Instance of DAO for agreement.
     */
    @Autowired
    private AgreementDao agreementDao;

    /**
     * Setter for field agreementDao.
     * @param agreementDaoIn - new value for that field.
     */
    public void setAgreementDao(final AgreementDao agreementDaoIn) {
        this.agreementDao = agreementDaoIn;
    }

    /**
     * Function to get all agreements.
     * @return list of all agreements, null if error.
     */
    public List<AgreementEntity> getAll() {
        return this.agreementDao.getAll();
    }

    /**
     * Removes agreement from DB.
     * (if it is active - sets to nonactive)
     * (if it is nonactive - remove)
     * @param id - id of the agreement to remove
     * @return true if all ok, false if could not perform operation
     */
    public boolean removeObject(final int id) {
        AgreementEntity agreement = agreementDao.findObjectById(id);
        if (agreement != null) {
            if (agreement.getActive()) {
                agreement.setActive(false);
                return updateObject(agreement);
            } else {
                return agreementDao.removeObject(id);
            }
        }
        return false;
    }

    /**
     * Function to find agreement by id.
     * @param id - id of the object to find
     * @return object from DB with id from argument
     */
    public AgreementEntity findObjectById(final int id) {
        return this.agreementDao.findObjectById(id);
    }

    /**
     * Updates object input in DB.
     * @param input - object to update
     * @return true if all ok, false if could not perform operation
     */
    public boolean updateObject(final AgreementEntity input) {
        return this.agreementDao.updateObject(input);
    }

    /**
     * Adds object input to DB.
     * @param input - entity to add
     * @return true if all ok, false if could not perform operation
     */
    public boolean addObject(final AgreementEntity input) {
        return this.agreementDao.addObject(input);
    }

    /**
     * Function to get all active agreements.
     * @return list of active agreements
     */
    public List<AgreementEntity> getActive() {
        return this.agreementDao.getActive();
    }

    /**
     * Function to get all nonactive agreements.
     * @return list of nonactive agreements
     */
    public List<AgreementEntity> getNonactive() {
        return this.agreementDao.getPassive();
    }
}
