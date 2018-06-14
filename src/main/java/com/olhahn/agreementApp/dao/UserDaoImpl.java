package com.olhahn.agreementApp.dao;

import com.olhahn.agreementApp.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 6/10/18
 **/
@Transactional
@Repository
public class UserDaoImpl extends AbstructDaoImpl<UserEntity> implements UserDao {

    public UserDaoImpl() {
        super();
        super.setClassType(UserEntity.class);
    }


    public List<UserEntity> getUsers() {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("select * from users");
            List<Object[]> a = query.list();
            List<UserEntity> users = new ArrayList<UserEntity>();
            for(Object[] i: a){
                UserEntity user = new UserEntity();
                user.setId((Integer)i[0]);
                user.setName((String)i[1]);
                user.setPassword((String)i[2]);
                users.add(user);
            }

            tx.commit();
            return users;
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
