package com.zuehlke.neverforget.service;

import com.zuehlke.neverforget.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @PostAuthorize("returnObject == null or returnObject.owner.username == principal.username")
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public Category createCategory(Category category) {
        category.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return categoryRepository.save(category);
    }

    @PostAuthorize("returnObject.owner.username == principal.username")
    public Category updateCategory(Long id, Category category) {
        Category currentCategory = categoryRepository.findOne(id);
        currentCategory.setName(category.getName());
        currentCategory.setDescription(category.getDescription());
        return categoryRepository.save(currentCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

}
