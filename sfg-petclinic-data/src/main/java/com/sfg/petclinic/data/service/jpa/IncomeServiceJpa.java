package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Income;
import com.sfg.petclinic.data.repository.IncomeRepository;
import com.sfg.petclinic.data.service.IncomeService;

@Service
@Profile("jpa")
public class IncomeServiceJpa implements IncomeService {

    private IncomeRepository incomeRepository;
    
    @Autowired
    public IncomeServiceJpa(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public Set<Income> findAll() {
        Set<Income> incomes = new HashSet<>();
        incomeRepository.findAll().forEach(incomes::add);
        return incomes;
    }

    @Override
    public Income findById(Long id) {
        return incomeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Income income) {
        incomeRepository.delete(income);
        
    }

    @Override
    public void deleteById(Long id) {
        incomeRepository.deleteById(id);
    }
}
