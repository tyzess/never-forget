package com.zuehlke.neverforget.controller;


import com.zuehlke.neverforget.domain.Category;
import com.zuehlke.neverforget.service.CategoryService;
import com.zuehlke.neverforget.service.HALService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HALService halService;


    ////////////////////////////////////
    //CRUD Routes

    @GetMapping("/")
    public ResponseEntity<Resources<Resource<Category>>> getCategories() {
        return ResponseEntity.ok(halService.categoryToResource(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Category>> getCategory(@PathVariable Long id) {
        Category category = categoryService.findOne(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(halService.categoryToResource(category));
    }

    @PostMapping()
    public ResponseEntity<Resource<Category>> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.created(halService.getUriFromCategory(createdCategory)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Category>> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource<Category>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
