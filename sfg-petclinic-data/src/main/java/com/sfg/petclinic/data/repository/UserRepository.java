package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByName(String name);

}
