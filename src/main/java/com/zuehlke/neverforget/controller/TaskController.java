package com.zuehlke.neverforget.controller;

import com.zuehlke.neverforget.service.CategoryService;
import com.zuehlke.neverforget.service.HALService;
import com.zuehlke.neverforget.service.TaskService;
import com.zuehlke.neverforget.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HALService halService;


    ////////////////////////////////////
    // CRUD Routes

    @GetMapping("/")
    public ResponseEntity<Resources<Resource<Task>>> getTasks() {
        return ResponseEntity.ok(halService.taskToResource(taskService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Task>> getTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if (task == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(halService.taskToResource(task));
    }

    @PostMapping("/")
    public ResponseEntity<Resource<Task>> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.created(halService.getUriFromTask(createdTask)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Task>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource<Task>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }


    ////////////////////////////////////
    // Special routes

    @PostMapping("/{id}/check")
    public ResponseEntity<Resource<Task>> checkTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return ResponseEntity.notFound().build();
        taskService.setChecked(task, true);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/{id}/uncheck")
    public ResponseEntity<Resource<Task>> uncheckTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return ResponseEntity.notFound().build();
        taskService.setChecked(task, false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<Resource<Category>> getTaskCategory(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(halService.categoryToResource(task.getCategory()));
    }

    @PostMapping("/{id}/category") //XXX how to set no category? null-category vs empty/default-category
    public ResponseEntity<Resource<Task>> setTaskCategory(@RequestParam(name = "category_id") Long category_id, @PathVariable Long id) {
        Task task = taskService.findOne(id);
        Category category = categoryService.findOne(category_id);
        if(task == null || category == null)
            return ResponseEntity.notFound().build();
        task = taskService.setCategory(task, category);
        return ResponseEntity.created(halService.getUriFromTask(task)).build();
    }

    @GetMapping("/{id}/parent")
    public ResponseEntity<Resource<Task>> getTaskParent(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(halService.taskToResource(task.getParent()));
    }

    //XXX don't allow circular relations!
    @PostMapping("/{id}/parent")
    public ResponseEntity<Resource<Task>> setTaskParent(@RequestParam(name = "parent_id") Long parent_id, @PathVariable Long id) {
        Task task = taskService.findOne(id);
        Task parent = taskService.findOne(parent_id);
        if(task == null || parent == null)
            return ResponseEntity.notFound().build();
        task = taskService.setParent(task, parent);
        return ResponseEntity.created(halService.getUriFromTask(task)).build();
    }
}