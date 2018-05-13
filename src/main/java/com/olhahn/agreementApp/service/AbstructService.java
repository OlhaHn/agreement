package com.olhahn.agreementApp.service;

import java.util.List;

/**
 * Abstruct service interface for standard DB functions.
 * @param <T>
 */
public interface AbstructService<T> {

    /**
     *
     * @return all elements
     */
    List<T> getAll();

    /**
     * Removes object from DB
     * @param id
     */
    void removeObject(int id);

    /**
     *
     * @param id
     * @return object from DB
     */
    T findObjectById(int id);

    /**
     * Updates DB object
     * @param input
     */
    void updateObject(T input);

    /**
     * Adds object into DB
     * @param input
     */
    void addObject(T input);
}
