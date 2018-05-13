package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.dao.AgreementDao;
import com.olhahn.agreementApp.model.AgreementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of AgreementService.
 */
@Service
public class AgreementServiceImpl implements AgreementService {

    /**
     * Instance of DAO for agreement
     */
    @Autowired
    private AgreementDao agreementDao;

    /**
     * setter
     * @param agreementDao
     */
    public void setAgreementDao(AgreementDao agreementDao) {
        this.agreementDao = agreementDao;
    }

    /**
     *
     * @return list of all agreements
     */
    public List<AgreementEntity> getAll() {
        return this.agreementDao.getAll();
    }

    /**
     * removes agreement from DB
     * @param id
     */
    public void removeObject(int id) {
        this.agreementDao.removeObject(id);
    }

    /**
     *
     * @param id
     * @return object from DB with id from argument
     */
    public AgreementEntity findObjectById(int id) {
        return this.agreementDao.findObjectById(id);
    }

    /**
     * Updates object input in DB
     * @param input
     */
    public void updateObject(AgreementEntity input) {
        this.agreementDao.updateObject(input);
    }

    /**
     * Adds object input to DB
     * @param input
     */
    public void addObject(AgreementEntity input) {
        this.agreementDao.addObject(input);
    }

    /**
     *
     * @return list of active agreements
     */
    public List<AgreementEntity> getActive() {
        return this.agreementDao.getActive();
    }

    /**
     *
     * @return list of nonactive agreements
     */
    public List<AgreementEntity> getNonactive() {
        return this.agreementDao.getPassive();
    }
}
