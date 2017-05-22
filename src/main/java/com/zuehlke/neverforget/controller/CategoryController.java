package com.zuehlke.neverforget.controller;


import com.zuehlke.neverforget.domain.CategoryRepository;
import com.zuehlke.neverforget.domain.TaskRepository;
import com.zuehlke.neverforget.service.CategoryService;
import com.zuehlke.neverforget.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;
}
