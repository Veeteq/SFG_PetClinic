package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Category;
import com.sfg.petclinic.data.repository.CategoryRepository;
import com.sfg.petclinic.data.service.CategoryService;

import lombok.extern.log4j.Log4j2;

@Service
@Profile("jpa")
@Log4j2
public class CategoryServiceJpa implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceJpa(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Set<Category> findAll() {
		Set<Category> categories = new HashSet<>();
		categoryRepository.findAll().iterator().forEachRemaining(categories::add);;
		return categories;
	}

	@Override
	public Category findById(Long id) {
	    log.info("CategoryServiceJpa: findById: " + id);
		Optional<Category> optional = categoryRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}
}
