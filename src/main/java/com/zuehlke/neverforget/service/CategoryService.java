package com.zuehlke.neverforget.service;

import com.zuehlke.neverforget.domain.Category;
import com.zuehlke.neverforget.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostAuthorize("returnObject == null or returnObject.owner.username == principal.username")
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }
}
