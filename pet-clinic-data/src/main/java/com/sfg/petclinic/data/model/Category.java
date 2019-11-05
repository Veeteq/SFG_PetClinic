package com.sfg.petclinic.data.model;

public class Category extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    private CategoryType type;

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
