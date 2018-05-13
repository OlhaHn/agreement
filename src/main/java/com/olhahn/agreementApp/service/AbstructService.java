package com.olhahn.agreementApp.service;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Abstruct service interface for standard DB functions.
 * @param <T> - entity class
 */
public interface AbstructService<T> {

    /**
     * Function which returns all entities (T class).
     * @return all elements
     */
    List<T> getAll();

    /**
     * Removes object from DB.
     * @param id id of the object
     * @return true if removed, false otherwise
     */
    boolean removeObject(int id);

    /**
     * Find object having it's id.
     * @param id - id of the object to find
     * @return object from DB, null if does not exist.
     */
    T findObjectById(int id);

    /**
     * Updates DB object.
     * @param input - object to update
     * @return true if updated
     */
    boolean updateObject(T input);

    /**
     * Adds object into DB.
     * @param input - object to add
     * @return true if added
     */
    boolean addObject(T input);
}
