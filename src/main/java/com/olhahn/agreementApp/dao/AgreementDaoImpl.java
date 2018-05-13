package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.AgreementEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 * Implementation of AgreementDao interface.
 */
@Transactional
@Repository
public class AgreementDaoImpl extends AbstructDaoImpl<AgreementEntity>
        implements AgreementDao {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AgreementDaoImpl.class);

    /**
     * Constructor without arguments.
     * Sets AgreementEntity class as parameter into AbstructDaoImpl.
     */
    public AgreementDaoImpl() {
        super();
        super.setClassType(AgreementEntity.class);
    }

    /**
     * Function to get active agreements.
     * @return list of active agreements
     */
    public List<AgreementEntity> getActive() {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<AgreementEntity> result = session.
                    getNamedQuery("findActiveAgreements").list();
            tx.commit();
            for (AgreementEntity en: result) {
                LOGGER.info("Active agreement: " + en.toString());
            }
            return result;
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
     * Function to get nonactive agreements.
     * @return list of nonactive agreements
     */
    public List<AgreementEntity> getPassive() {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<AgreementEntity> result = session.
                    getNamedQuery("findNonactiveAgreements").list();
            tx.commit();
            for (AgreementEntity en: result) {
                LOGGER.info("Passive agreement: " + en.toString());
            }
            return result;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }
}
