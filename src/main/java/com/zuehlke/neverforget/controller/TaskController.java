package com.zuehlke.neverforget.controller;

import com.zuehlke.neverforget.service.CategoryService;
import com.zuehlke.neverforget.service.TaskService;
import com.zuehlke.neverforget.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;


    // CRUD Routes

    @GetMapping("/")
    public ResponseEntity<Iterable<Task>> getTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if (task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Special routes

    @PostMapping("/{id}/check")
    public ResponseEntity<Task> checkTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        task = taskService.setChecked(task, true);
        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @PostMapping("/{id}/uncheck")
    public ResponseEntity<Task> uncheckTask(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        task = taskService.setChecked(task, false);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<Category> getTaskCategory(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(task.getCategory(), HttpStatus.OK);
    }

    @PostMapping("/{id}/category") //XXX how to set no category? null-category vs empty/default-category
    public ResponseEntity<Task> setTaskCategory(@RequestParam(name = "category_id") Long category_id, @PathVariable Long id) {
        Task task = taskService.findOne(id);
        Category category = categoryService.findOne(category_id);
        if(task == null || category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        task = taskService.setCategory(task, category);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/parent")
    public ResponseEntity<Task> getTaskParent(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(task.getParent(), HttpStatus.OK);
    }

    //XXX don't allow circular relations!
    @PostMapping("/{id}/parent")
    public ResponseEntity<Task> setTaskParent(@RequestParam(name = "parent_id") Long parent_id, @PathVariable Long id) {
        Task task = taskService.findOne(id);
        Task parent = taskService.findOne(parent_id);
        if(task == null || parent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        task = taskService.setParent(task, parent);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }


    // Search routes

//    @GetMapping()
//    public List<Task> findByName(String name) {
//        return null;
//    }

}