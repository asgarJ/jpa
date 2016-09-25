package com.javacodegeeks.ultimate;

import javax.persistence.*;

/**
 * Created by asgar on 9/24/16.
 */
@Entity
@Table(name = "T_PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private IdCard idCard;

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

    @OneToOne
    @JoinColumn(name = "ID_CARD_ID")
    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
