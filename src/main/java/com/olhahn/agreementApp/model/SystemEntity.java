package com.olhahn.agreementApp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/3/18
 *
 * SystemEntity - class, which represents System.
 **/
@Entity
@Table(name = "system")
@NamedQuery(name = "findByName",
            query = "from SystemEntity where name = :name")
@SequenceGenerator(name = "system_id_seq", sequenceName = "system_id_seq")
public class SystemEntity implements Serializable {

    /**
     * Id of the system in db.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Name of the system in db.
     */
    @Column(name = "name")
    @NonNull
    private String name;

    /**
     * Description of the system.
     */
    @Column(name = "description")
    private String description;

    /**
     * Id of the owner of the system.
     */
    @Column(name = "owner")
    @NonNull
    private int owner;

    /**
     * Constructor without arguments, do nothing.
     */
    public SystemEntity() { }

    /**
     * Constructor with arguments,
     * creates new systems with parameters from arguments.
     * @param id id of the system
     * @param name name of the system
     * @param description description of the system
     * @param owner id of the owner of the system
     */
    public SystemEntity(int id, String name, String description, int owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    // Getters and setters

    /**
     * Setter for field description.
     * @param description new value for the field
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for field description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for field id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for field owner.
     * @return owner's id
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Getter for field name.
     * @return name of the system
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for field name.
     * @param name new value for the field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for field owner.
     * @param owner new value for the field
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }

    /**
     * toString function for SystemEntity class.
     * @return name and description
     */
    @Override
    public String toString() {
        return name + " " + description;
    }

    /**
     * Setter for id field.
     * @param id new value for the field
     */
    public void setId(int id) {
        this.id = id;
    }
}
