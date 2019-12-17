package com.sfg.petclinic.data.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fin_events")
@AttributeOverride(name = "id", column = @Column(name = "evnt_id"))
@AttributeOverride(name = "name", column = @Column(name = "evnt_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "evnt_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class FinancialEvent extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "evnt_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cprt_id", nullable=false)    
    private Counterparty counterparty;
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @Size(min = 1)
    private List<Book> books = new ArrayList<>();
    
    @Override
    @NotEmpty
    public String getName() {
        return super.getName();
    }
}
