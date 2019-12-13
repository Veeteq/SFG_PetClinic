package com.sfg.petclinic.data.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Expense;
import com.sfg.petclinic.data.repository.ExpenseRepository;
import com.sfg.petclinic.data.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

}
