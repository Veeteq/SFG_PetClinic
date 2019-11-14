package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.User;

public interface UserService extends CrudService<User, Long>{

    User findByName(String name);

}
