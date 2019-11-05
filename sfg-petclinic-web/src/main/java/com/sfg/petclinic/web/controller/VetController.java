package com.sfg.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/vets")
public class VetController {

    @RequestMapping(path = {"", "/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String getVets() {
        return "vets/index"; 
    }
}
