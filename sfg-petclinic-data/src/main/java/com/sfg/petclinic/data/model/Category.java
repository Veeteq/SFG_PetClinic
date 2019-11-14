package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@AttributeOverride(name = "id", column = @Column(name = "cate_id"))
@AttributeOverride(name = "name", column = @Column(name = "cate_name_tx"))
@SequenceGenerator(name="default_seq", sequenceName="cate_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    @Builder
    public Category(Long id, String name) {
        super(id, name);
    }
    
    @Column(name = "cate_type_tx")
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;
}
