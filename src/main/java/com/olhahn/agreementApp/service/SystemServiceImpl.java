package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.dao.SystemDao;
import com.olhahn.agreementApp.model.SystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Implementation of SystemService interface.
 */
@Service
public class SystemServiceImpl implements SystemService {

    /**
     * Dao for sysyem.
     */
    @Autowired
    private SystemDao systemDao;

    /**
     * Setter for systemDao field.
     * @param systemDaoIn - new value for the field
     */
    public void setSystemDao(final SystemDao systemDaoIn) {
        this.systemDao = systemDaoIn;
    }

    /**
     * Function to get all systems.
     * @return list of all systems,
     * null if something went wrong
     */
    public List<SystemEntity> getAll() {
        return this.systemDao.getAll();
    }

    /**
     * Removes system from DB.
     * @param id - id of the system to remove
     * @return true if removed, false if something went wrong
     */
    public boolean removeObject(final int id) {
        return this.systemDao.removeObject(id);
    }

    /**
     * Function to find system by id.
     * @param id - id of the system to find.
     * @return object from DB with id as in parameter
     */
    public SystemEntity findObjectById(final int id) {
        return this.systemDao.findObjectById(id);
    }

    /**
     * Updates object in DB.
     * @param input - system to update.
     * @return true if updated, false if something went wrong.
     */
    public boolean updateObject(final SystemEntity input) {
        return this.systemDao.updateObject(input);
    }

    /**
     * Adds object into DB.
     * @param input - object to add
     * @return true if added, false if something went wrong.
     */
    public boolean addObject(final SystemEntity input) {
        return this.systemDao.addObject(input);
    }

    /**
     * Finds system by it's name.
     * @param name of system to find
     * @return system entity, if system with such name is in db, null otherwise
     */
    public SystemEntity getSystemByName(final String name) {
        return this.systemDao.getSystemByName(name);
    }
}
