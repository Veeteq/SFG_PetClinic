package com.sfg.petclinic.data.service.jpa;

import java.util.HashSet;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.FinancialEvent;
import com.sfg.petclinic.data.repository.FinancialEventRepository;
import com.sfg.petclinic.data.service.FinancialEventService;

@Service
@Profile("jpa")
public class FinanacialEventServiceImpl implements FinancialEventService {

    private final FinancialEventRepository financialEventRepository;
    
    @Autowired
    public FinanacialEventServiceImpl(FinancialEventRepository financialEventRepository) {
        this.financialEventRepository = financialEventRepository;
    }

    @Override
    public Set<FinancialEvent> findAll() {
        Set<FinancialEvent> financialEvents = new HashSet<>();
        financialEventRepository.findAll().forEach(financialEvents::add);
        return financialEvents;
    }

    @Override
    public FinancialEvent findById(Long id) {
        FinancialEvent financialEvent = financialEventRepository.findById(id).orElse(null);
        return financialEvent;
    }

    @Override
    @Transactional
    public FinancialEvent save(FinancialEvent financialEvent) {
        return financialEventRepository.save(financialEvent);        
    }

    @Override
    public void delete(FinancialEvent financialEvent) {
        financialEventRepository.delete(financialEvent);
    }

    @Override
    public void deleteById(Long id) {
        financialEventRepository.deleteById(id);
    }

    @Override
    public Set<FinancialEvent> findAllByNameLike(String name) {
        return financialEventRepository.findAllByNameLike(name);
    }
}
