package com.sfg.petclinic.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfg.petclinic.data.model.Expense;
import com.sfg.petclinic.data.model.Item;
import com.sfg.petclinic.data.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(path = "/budget")
public class BudgetController {

    private static final String VIEWS_ITEMS = "budget/items";
    private static final String VIEWS_FIND_EVENTS = "budget/findEvents";
    
    private final ItemService itemService;

    @Autowired
    public BudgetController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ModelAttribute(name = "items")
    public Collection<Item> getItems() {
        log.debug("BudgetController: getItems");
        return itemService.findAll();
    }
    
    @GetMapping(path = "/items")
    public String listItems(Model model) {
        log.debug("BudgetController: listItems");
        return VIEWS_ITEMS;
    }
    
    @GetMapping(path = "/events/find")
    public String showFindEventForm(Model model) {
        log.debug("BudgetController: showFindEventForm");
        
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        
        return VIEWS_FIND_EVENTS;
    }
}
