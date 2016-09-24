package com.javacodegeeks.ultimate;

import javax.persistence.*;

/**
 * Created by asgar on 9/24/16.
 */
@Entity
@Table(name = "T_PERSON")
@DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public class Person {
    private Long id;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "FIRST_NAME", length = 100, nullable = false, unique = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
