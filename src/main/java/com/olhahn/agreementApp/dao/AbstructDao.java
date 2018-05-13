package com.olhahn.agreementApp.dao;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Interface AbstructDao,
 * set of standard functionality for interaction with DB.
 * @param <T> - entity
 */
public interface AbstructDao<T> {

    /**
     * Returns list of all entities.
     * @return all elements of type T
     */
    List<T> getAll();

    /**
     * Removes object from DB.
     * @param id of the object
     */
    void removeObject(int id);

    /**
     * Finds object by id in DB.
     * @param id - id of the object to find.
     * @return object with that id
     */
    T findObjectById(int id);

    /**
     * Updates object in DB.
     * @param input object to update
     */
    void updateObject(T input);

    /**
     * Adds object into DB.
     * @param input object to add
     */
    void addObject(T input);
}
