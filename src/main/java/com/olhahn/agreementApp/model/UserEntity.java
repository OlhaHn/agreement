package com.olhahn.agreementApp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 6/10/18
 **/

@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq")
public class UserEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "password")
    @NonNull
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
