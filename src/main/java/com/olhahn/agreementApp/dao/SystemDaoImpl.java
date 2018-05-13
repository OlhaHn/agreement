package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.SystemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Implementation of SystemDao interface.
 */
@Transactional
@Repository
public class SystemDaoImpl extends AbstructDaoImpl<SystemEntity>
        implements SystemDao {

    /**
     * Constructor without parameters.
     * Sets SystemEntity into AbstructDaoImpl.
     */
    public SystemDaoImpl() {
        super();
        super.setClassType(SystemEntity.class);
    }

    /**
     * Find System with name from argument.
     * @param name of system to return
     * @return list of systems with name from argument
     */
    public List<SystemEntity> getSystemByName(final String name) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.getNamedQuery("findByName");
            query.setParameter("name", name);
            @SuppressWarnings("unchecked")
            List<SystemEntity> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null; //TODO throw exception
        } finally {
            session.close();
        }
    }
}
