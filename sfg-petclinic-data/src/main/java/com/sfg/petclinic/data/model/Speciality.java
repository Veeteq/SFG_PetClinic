package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="specialities")
@AttributeOverride(name="id", column=@Column(name="speciality_id"))
@SequenceGenerator(name="default_seq", sequenceName="speciality_seq", allocationSize=1)
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class Speciality extends NamedEntity {

    private static final long serialVersionUID = 1L;

}
