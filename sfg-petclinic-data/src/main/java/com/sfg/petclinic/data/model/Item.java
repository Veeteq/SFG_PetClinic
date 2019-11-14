package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
@AttributeOverride(name = "name", column = @Column(name = "item_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "item_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @Builder
    public Item(Long id, String name) {
        super(id, name);
    }

	@ManyToOne
	@JoinColumn(name="cate_id")
	private Category category;
}
