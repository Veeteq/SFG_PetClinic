package com.sfg.petclinic.data.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.service.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }
}
