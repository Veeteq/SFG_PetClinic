package com.sfg.petclinic.data.model;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="visits")
@AttributeOverride(name = "id", column = @Column(name = "visit_id"))
@SequenceGenerator(name="default_seq", sequenceName="visit_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Visit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "visit_date")
    private LocalDate date;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
