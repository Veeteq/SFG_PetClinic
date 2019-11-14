package com.sfg.petclinic.data.service.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.User;
import com.sfg.petclinic.data.service.UserService;

@Service
@Profile(value = {"default","map"})
public class UserServiceMap extends AbstractMapService<User, Long> implements UserService {

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public User save(User user) {
        return super.save(user);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public User findByName(String name) {
        return null;
    }
}
