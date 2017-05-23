package com.zuehlke.neverforget.service;

import com.zuehlke.neverforget.controller.CategoryController;
import com.zuehlke.neverforget.controller.TaskController;
import com.zuehlke.neverforget.domain.Category;
import com.zuehlke.neverforget.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class HALService {

    @Autowired
    private EntityLinks entityLinks;


    public URI getUriFromTask(Task task) {
        Link link = entityLinks.linkToSingleResource(Task.class, task.getId()).expand();
        return URI.create(link.getHref());
    }

    public URI getUriFromCategory(Category category) {
        Link link = entityLinks.linkToSingleResource(Category.class, category.getId()).expand();
        return URI.create(link.getHref());
    }

    public Resources<Resource<Task>> taskToResource(List<Task> tasks) {
        Link selfLink = linkTo(methodOn(TaskController.class).getTasks()).withSelfRel();
        List<Resource<Task>> taskResources = tasks.stream().map(task -> taskToResource(task)).collect(Collectors.toList());
        return new Resources<>(taskResources, selfLink);
    }

    public Resource<Task> taskToResource(Task task) {
        if (task == null)
            return null;
        ResponseEntity<Resource<Task>> entity = methodOn(TaskController.class).getTask(task.getId());
        Link selfLink = linkTo(entity).withSelfRel();
        Link category = linkTo(methodOn(TaskController.class).getTaskCategory(task.getId())).withRel("category");
        Link parent = linkTo(methodOn(TaskController.class).getTaskParent(task.getId())).withRel("parent");
        return new Resource<>(task, selfLink, category, parent);
    }

    public Resources<Resource<Category>> categoryToResource(List<Category> categories) {
        Link selfLink = linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel();
        List<Resource<Category>> categoryResources = categories.stream().map(category -> categoryToResource(category)).collect(Collectors.toList());
        return new Resources<>(categoryResources, selfLink);
    }

    public Resource<Category> categoryToResource(Category category) {
        if (category == null)
            return null;
        ResponseEntity<Resource<Category>> entity = methodOn(CategoryController.class).getCategory(category.getId());
        Link selfLink = linkTo(entity).withSelfRel();
        return new Resource<>(category, selfLink);
    }
}
