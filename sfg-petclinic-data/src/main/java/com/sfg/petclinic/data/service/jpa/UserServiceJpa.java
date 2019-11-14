package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.User;
import com.sfg.petclinic.data.repository.UserRepository;
import com.sfg.petclinic.data.service.UserService;

@Service
@Profile("jpa")
public class UserServiceJpa implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceJpa(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Set<User> findAll() {
		Set<User> users = new HashSet<>();
		userRepository.findAll().iterator().forEachRemaining(users::add);;
		return users;
	}

	@Override
	public User findById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
	    userRepository.delete(user);
	}

	@Override
	public void deleteById(Long id) {
	    userRepository.deleteById(id);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
}
