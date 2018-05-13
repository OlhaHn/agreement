package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.dao.SystemDao;
import com.olhahn.agreementApp.model.SystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of SystemService interface.
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDao systemDao;

    /**
     * Setter for systemDao field.
     * @param systemDao
     */
    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    /**
     *
     * @return list of all systems
     */
    public List<SystemEntity> getAll() {
        return this.systemDao.getAll();
    }

    /**
     * removes object from DB
     * @param id
     */
    public void removeObject(int id) {
        this.systemDao.removeObject(id);
    }

    /**
     *
     * @param id
     * @return object from DB with id as in parameter
     */
    public SystemEntity findObjectById(int id) {
        return this.systemDao.findObjectById(id);
    }

    /**
     * updates object in DB
     * @param input
     */
    public void updateObject(SystemEntity input) {
        this.systemDao.updateObject(input);
    }

    /**
     * adds object into DB
     * @param input
     */
    public void addObject(SystemEntity input) {
        this.systemDao.addObject(input);
    }

    /**
     *
     * @param name of system to find
     * @return system entity, if system with such name is in db, null otherwise
     */
    public SystemEntity getSystemByName(String name) {
        List<SystemEntity> list = this.systemDao.getSystemByName(name);
        return list.isEmpty() ? null : list.get(0);
    }
}
