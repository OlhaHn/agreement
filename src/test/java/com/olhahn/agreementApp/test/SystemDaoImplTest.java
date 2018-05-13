package com.olhahn.agreementApp.test;

import com.olhahn.agreementApp.dao.SystemDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/13/18
 **/
class SystemDaoImplTest {

    SessionFactory sessionFactory;

    @BeforeAll
    private void setSessionFactory(){
        
    }

    @Test
    void getSystemByName() {
    }

    @Test
    void getAll() {
    }

    @Test
    void removeObject() {
    }

    @Test
    void findObjectById() {
    }

    @Test
    void updateObject() {
    }

    @Test
    void addObject() {
    }
}