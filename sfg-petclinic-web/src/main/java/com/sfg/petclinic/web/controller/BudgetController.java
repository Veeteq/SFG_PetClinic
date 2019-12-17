package com.sfg.petclinic.web.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sfg.petclinic.data.model.Book;
import com.sfg.petclinic.data.model.FinancialEvent;
import com.sfg.petclinic.data.model.Item;
import com.sfg.petclinic.data.service.FinancialEventService;
import com.sfg.petclinic.data.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(path = "/budget")
public class BudgetController {

    private static final String VIEWS_ITEMS = "budget/items";
    private static final String VIEWS_EVENTS_FIND = "budget/eventFind";
    private static final String VIEWS_EVENTS_LIST = "budget/eventDetails";
    private static final String VIEWS_EVENTS_DETAILS = "budget/eventDetails";
    private static final String VIEWS_EVENTS_ADD_UPDATE = "budget/addOrUpdateEvent";
    
    private final ItemService itemService;
    private final FinancialEventService financialEventService;

    @Autowired
    public BudgetController(ItemService itemService, FinancialEventService financialEventService) {
        this.itemService = itemService;
        this.financialEventService = financialEventService;
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
    
    @GetMapping(path = "/events")
    public String showFindResultsEventForm(FinancialEvent financialEvent, BindingResult result, Model model) {
        log.debug("BudgetController: showFindResultsEventForm");
        
        if(financialEvent.getName() == null) {
            financialEvent.setName("");
        }
        
        String name = financialEvent.getName();
        Set<FinancialEvent> financialEvents = financialEventService.findAllByNameLike(name);
        if(financialEvents.isEmpty()) {
            log.debug("BudgetController: financialEvents.isEmpty()");
            result.rejectValue("name", "notFound", "Not Found!");
            return VIEWS_EVENTS_FIND;
        } else if (financialEvents.size() == 1) {
            log.debug("OwnerController: owners.size() == 1");
            financialEvent = financialEvents.iterator().next();
            return "redirect:/budget/events/" + financialEvent.getId();
        } else {
            log.debug("OwnerController: owners > 1");
            model.addAttribute("financialEvents", financialEvents);
            return VIEWS_EVENTS_LIST;
        }
    }

    @GetMapping(path = "/events/find")
    public String showFindEventForm(Model model) {
        log.debug("BudgetController: showFindEventForm");
        
        FinancialEvent financialEvent = new FinancialEvent();
        model.addAttribute("financialEvent", financialEvent);
        
        return VIEWS_EVENTS_FIND;
    }

    @GetMapping(path = "/events/new")
    public String addOrUpdateEventFormShow(Model model) {
        log.debug("BudgetController: showAddOrUpdateEventForm");
        
        FinancialEvent financialEvent = new FinancialEvent();
        financialEvent.setEventDate(LocalDate.now());
        
        model.addAttribute("financialEvent", financialEvent);
        
        return VIEWS_EVENTS_ADD_UPDATE;
    }
    
    @PostMapping(path = "/events/new")
    public String addOrUpdateEventFormPost(@Valid @ModelAttribute(name = "financialEvent") FinancialEvent financialEvent, BindingResult result, Model model) {
        log.debug("BudgetController: addOrUpdateFinancialEvent");

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> log.debug(error.toString()));
            return VIEWS_EVENTS_ADD_UPDATE;
        }

        financialEvent.getBooks().forEach(book -> book.setEvent(financialEvent));
        
        FinancialEvent savedFinancialEvent = financialEventService.save(financialEvent);
        
        return "redirect:/budget/events/" + savedFinancialEvent.getId();
    }

    @PostMapping(path = {"/events/new","/events/{financialEventId}/edit"}, params = {"addBook"})
    public String addBookToFinancialEvent(@Valid @ModelAttribute(name = "financialEvent") final FinancialEvent financialEvent, final BindingResult result) {
        log.debug("BudgetController: addBookToFinancialEvent");
        
        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> log.debug(error.toString()));
        }

        financialEvent.getBooks().add(Book.builder().event(financialEvent).build());
        
        return VIEWS_EVENTS_ADD_UPDATE;
    } 

    @GetMapping(path = {"/events/{financialEventId}"})
    public ModelAndView showFinancialEventById(@PathVariable(name = "financialEventId") Long financialEventId) {
        log.debug("BudgetController: showFinancialEventById");
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEWS_EVENTS_DETAILS);
        modelAndView.addObject(financialEventService.findById(financialEventId));
        
        return modelAndView;
    }    
    
    @GetMapping(path = {"/events/{financialEventId}/edit"})
    public ModelAndView editEventFormShow(@PathVariable(name = "financialEventId") Long financialEventId) {
        log.debug("BudgetController: editEventFormShow");
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEWS_EVENTS_ADD_UPDATE);
        FinancialEvent financialEvent = financialEventService.findById(financialEventId);
        modelAndView.addObject(financialEvent);
        
        return modelAndView;
    }    

    @PostMapping(path = {"/events/{financialEventId}/edit"})
    public String editEventFormPost(@PathVariable(name = "financialEventId") Long financialEventId, @Valid FinancialEvent financialEvent, BindingResult result) {
        log.debug("BudgetController: editEventFormPost");
        
        if(result.hasErrors()) {
            return VIEWS_EVENTS_ADD_UPDATE;
        } else {
            //financialEvent.setId(financialEventId);
            financialEvent.getBooks().forEach(book -> book.setEvent(financialEvent));
            FinancialEvent savedFinancialEvent = financialEventService.save(financialEvent);
            return "redirect:/budget/events/" + savedFinancialEvent.getId();
        }
    }
}
