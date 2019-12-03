package com.sfg.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping(path = {"/", "", "index", "index.html"}, method = RequestMethod.GET)
    public String getIndex() {
        log.debug("IndexController: getIndex");
        return "index";
    }
    
    @RequestMapping(path = {"/oups"})
    public String findOwners() {
        log.debug("IndexController: findOwners");
        return "notimplemented";
    }
    
    @RequestMapping(path = {"/budget", "/budget/", "budget.html"})
    public String getBudgetPage() {
        log.debug("IndexController: getBudgetPage");
        return "budget";
    }
    
}
