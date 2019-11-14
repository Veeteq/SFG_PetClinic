package com.sfg.petclinic.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "name", column = @Column(name = "user_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "user_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User extends NamedEntity {

	private static final long serialVersionUID = 1L;
	
    @Builder
    public User(Long id, String name) {
        super(id, name);
    }
}
