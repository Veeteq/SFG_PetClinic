package com.sfg.petclinic.data.model;

public enum CategoryType {

    EXP(1, "Expense"),
    INC(2, "Income"),
    BOTH(3, "Both");

    private int id;
    private String description;
    
    private CategoryType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
