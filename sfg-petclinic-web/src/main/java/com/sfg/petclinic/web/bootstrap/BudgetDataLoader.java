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
import com.sfg.petclinic.data.model.Item;
import com.sfg.petclinic.data.model.User;
import com.sfg.petclinic.data.service.CategoryService;
import com.sfg.petclinic.data.service.ItemService;
import com.sfg.petclinic.data.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BudgetDataLoader implements CommandLineRunner {

    private final static Path baseDirectory = Paths.get("C:\\Users\\la289dm\\Downloads");
    
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    
    @Autowired
    public BudgetDataLoader(CategoryService categoryService, ItemService itemService, UserService userService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadItems();
        loadUsers();
    }

    private void loadCategories() {
        Path path = baseDirectory.resolve("categories.txt");
    	//String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Category category = Category.builder()
            .id(Long.valueOf(values[0]))
            .name(values[1])
            .categoryType(CategoryType.valueOf(values[2]))
            .build();
            categoryService.save(category);
        });      
        log.debug("Categories loaded...");
    }

    private void loadItems() {
        Path path = baseDirectory.resolve("items.txt");
        //String file = "F:\\categories.txt";
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            Item item = Item.builder()
            .id(Long.valueOf(values[0]))
            .category(categoryService.findById(Long.parseLong(values[1])))
            .name(values[2])
            .build();
            itemService.save(item);
        });      
        log.debug("Items loaded...");
    }

    private void loadUsers() {
        Path path = baseDirectory.resolve("users.txt");
        getStreamFromFile(path).forEach(line -> {
            String[] values = line.split("\t");
            User user = User.builder()
            .id(Long.valueOf(values[0]))
            .name(values[1])
            .build();
            userService.save(user);
        });      
        log.debug("Users loaded...");
    }
    
    private Stream<String> getStreamFromFile(Path path){
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}