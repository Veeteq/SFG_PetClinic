package com.sfg.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {

    @RequestMapping(path = {"", "/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String getVets() {
        return "categories/index"; 
    }
}
