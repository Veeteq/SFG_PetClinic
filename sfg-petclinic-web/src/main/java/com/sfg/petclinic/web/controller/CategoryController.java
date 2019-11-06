package com.sfg.petclinic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfg.petclinic.data.service.CategoryService;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {

    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = {"", "/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String getCategories(Model model) {
        System.out.println("CategoryController: getCategories");
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index"; 
    }
}
