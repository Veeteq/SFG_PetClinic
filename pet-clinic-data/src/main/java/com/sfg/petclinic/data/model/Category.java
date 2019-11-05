package com.sfg.petclinic.data.model;

public class Category extends NamedEntity {

    private CategoryType type;

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
