package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.repository.VetRepository;
import com.sfg.petclinic.data.service.VetService;

@Service
@Profile("jpa")
public class VetServiceJpa implements VetService{

    private VetRepository vetRepository;
    
    @Autowired
    public VetServiceJpa(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }
}
