package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pet_types")
@AttributeOverride(name = "id", column = @Column(name = "pet_type_id"))
@SequenceGenerator(name="default_seq", sequenceName="pet_type_seq", allocationSize=1)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PetType extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    @Builder
    public PetType(String name) {
        super(name);
    }
}
