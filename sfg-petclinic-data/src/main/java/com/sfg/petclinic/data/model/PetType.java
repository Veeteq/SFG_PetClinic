package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="pet_types")
@AttributeOverride(name = "id", column = @Column(name = "pet_type_id"))
@SequenceGenerator(name="default_seq", sequenceName="pet_type_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PetType extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
}
