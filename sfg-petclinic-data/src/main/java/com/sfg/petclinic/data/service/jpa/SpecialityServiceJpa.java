package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Speciality;
import com.sfg.petclinic.data.repository.SpecialityRepository;
import com.sfg.petclinic.data.service.SpecialityService;

@Service
@Profile("jpa")
public class SpecialityServiceJpa implements SpecialityService{

    private SpecialityRepository specialityRepository;
    
    @Autowired
    public SpecialityServiceJpa(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
