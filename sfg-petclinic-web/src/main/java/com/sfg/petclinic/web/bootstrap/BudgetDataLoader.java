package com.sfg.petclinic.web.bootstrap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sfg.petclinic.data.model.Category;
import com.sfg.petclinic.data.model.CategoryType;
import com.sfg.petclinic.data.service.CategoryService;

@Component
public class BudgetDataLoader implements CommandLineRunner {

    private final CategoryService categoryService;
    
    @Autowired
    public BudgetDataLoader(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
    }

    private void loadCategories() {
        //String file = "C:\\Users\\la289dm\\Downloads\\incomes\\categories.txt";
    	String file = "F:\\categories.txt";
        getStreamFromFile(file).forEach(line -> {
            String[] values = line.split("\t");
            Category category = new Category();
            category.setId(Long.valueOf(values[0]));
            category.setName(values[1]);
            category.setType(CategoryType.valueOf(values[2]));
            categoryService.save(category);
        });      
        System.out.println("Categories loaded...");
    }

    private Stream<String> getStreamFromFile(String file){
        try {
            Path path = Paths.get(file);
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}