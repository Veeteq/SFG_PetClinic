package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Item;
import com.sfg.petclinic.data.repository.ItemRepository;
import com.sfg.petclinic.data.service.ItemService;

@Service
@Profile("jpa")
public class ItemServiceJpa implements ItemService {

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceJpa(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public Set<Item> findAll() {
		Set<Item> items = new HashSet<>();
		itemRepository.findAll().iterator().forEachRemaining(items::add);;
		return items;
	}

	@Override
	public Item findById(Long id) {
		Optional<Item> optional = itemRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void delete(Item item) {
	    itemRepository.delete(item);
	}

	@Override
	public void deleteById(Long id) {
	    itemRepository.deleteById(id);
	}

	@Override
	public Item findByName(String name) {
		return itemRepository.findByName(name);
	}
}
