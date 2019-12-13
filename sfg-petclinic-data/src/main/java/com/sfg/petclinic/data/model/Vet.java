package com.sfg.petclinic.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="vets")
@AttributeOverride(name = "id", column = @Column(name = "vet_id"))
@SequenceGenerator(name="default_seq", sequenceName="vet_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Vet extends Person {

    private static final long serialVersionUID = 1L;

    public Vet(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities", 
               joinColumns = @JoinColumn(name = "vet_id"), 
               inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    @Builder.Default
    private Set<Speciality> specialities = new HashSet<>();
}
