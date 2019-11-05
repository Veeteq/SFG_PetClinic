package com.sfg.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OwnerController {

    @RequestMapping(path = {"/owners", "/owners/index", "/owners/index.html"}, method = RequestMethod.GET)
    public String getOwners() {
        return "owners/index"; 
    }
}
