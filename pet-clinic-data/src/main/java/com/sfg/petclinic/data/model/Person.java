package com.sfg.petclinic.data.model;

import com.sfg.petclinic.data.model.BaseEntity;

public class Person extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
