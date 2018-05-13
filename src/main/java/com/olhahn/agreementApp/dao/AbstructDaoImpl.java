package com.olhahn.agreementApp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Class AbstructDaoImpl,
 * implements standard functionality for interaction with DB.
 * @param <T> - entity
 */
@Repository
public class AbstructDaoImpl<T> implements AbstructDao<T> {

    /**
     * Logger field.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AbstructDaoImpl.class);

    /**
     * Session factory class from hibernate.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Class type of the entity from parameter.
     */
    private Class<T> classType;

    /**
     * Sets class type for generic.
     *
     * @param type the class type
     */
    void setClassType(final Class<T> type) {
        this.classType = type;
    }

    /**
     * Sets session factory.
     *
     * @param factory the session factory
     */
    public void setSessionFactory(final SessionFactory factory) {
        this.sessionFactory = factory;
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Returns list of objects from DB.
     *
     * @return list of all objects in table,
     * null if smth goes wrong
     */
     public List<T> getAll() {
         Session session = getSession();
         Transaction tx = null;
         try {
             tx = session.beginTransaction();
             @SuppressWarnings("unchecked")
             List<T> listOfObjects = session.
                     createQuery("from " + classType.getName()).list();
             for (T obj: listOfObjects) {
                 LOGGER.info("Object:" + obj.toString());
             }
             tx.commit();
             return listOfObjects;
         } catch (Exception e) {
             if (tx != null) {
                 tx.rollback();
             }
             return null;
         } finally {
             session.close();
         }
     }

    /**
     * Removes object from table in DB.
     *
     * @param id - id of the object to remove
     * @return true if removed, false if error
     * */
    public boolean removeObject(final int id) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings("unchecked")
            T object = (T) session.load(classType.getName(), id);
            if (object != null) {
                session.delete(object);
            }

            String objDescription = "";
            if (object != null) {
                objDescription = object.toString();
            }
            LOGGER.info("Object removed: " + objDescription);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            session.close();
        }
    }


    /**
     * Finds object in DB.
     *
     * @param id id of the object to find
     * @return object with that id
     */
    public T findObjectById(final int id) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Object o = session.load(classType.getName(),  id);
            tx.commit();
            if (o != null) {
                @SuppressWarnings("unchecked")
                T object = (T) o;
                String objDescription = object.toString();
                LOGGER.info("Object found: " + objDescription);
                return object;
            }
            LOGGER.info("Object not found: id=" + id);
            return null;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Updates object in DB.
     *
     * @param input - object to update
     * @return true if all ok, false otherwise
     */
    public boolean updateObject(final T input) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(input);
            tx.commit();
            LOGGER.info("Object updated: " + input.toString());
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Adds object "input" into DB.
     *
     * @param input - object to add.
     * @return true if all ok, false otherwise
     */
    public boolean addObject(final T input) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(input);
            tx.commit();
            LOGGER.info("Object saved: " + input.toString());
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            session.close();
        }
    }
}
