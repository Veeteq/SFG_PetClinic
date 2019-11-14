package com.sfg.petclinic.data.model;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class NamedEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public NamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }
    
    private String name;
}
