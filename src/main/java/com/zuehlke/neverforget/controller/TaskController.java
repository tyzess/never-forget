package com.zuehlke.neverforget.controller;

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
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskService taskService;


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
        taskService.createTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Special routes

    @PostAuthorize("returnObject.owner.id == principal.id")
    @PostMapping("/{id}/check")
    public ResponseEntity<Task> checkTask(@PathVariable Long id) {
        Task t = taskRepository.findOne(id);
        if(t != null) {
            t.setChecked(true);
            taskRepository.save(t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostAuthorize("returnObject.owner.id == principal.id")
    @PostMapping("/{id}/uncheck")
    public ResponseEntity<Task> uncheckTask(@PathVariable Long id) {
        Task t = taskRepository.findOne(id);
        if(t != null) {
            t.setChecked(false);
            taskRepository.save(t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostAuthorize("returnObject.owner.id == principal.id") //XXX own category as well
    @GetMapping("/{id}/category")
    public ResponseEntity<Category> getTaskCategory(@PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        if(task != null) {
            Category category = task.getCategory();
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostAuthorize("returnObject.owner.id == principal.id") //XXX own category as well
    @PostMapping("/{id}/category")
    public ResponseEntity<Task> setTaskCategory(@RequestParam(name = "category_id") Long category_id, @PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        Category category = categoryRepository.findOne(category_id);
        if(task != null || category != null) {
            task.setCategory(category);
            taskRepository.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostAuthorize("returnObject.owner.id == principal.id") //XXX own parent as well
    @GetMapping("/{id}/parent")
    public ResponseEntity<Task> getTaskParent(@PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        if(task != null) {
            Task parent = task.getParent();
            return new ResponseEntity<>(parent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostAuthorize("returnObject.owner.id == principal.id") //XXX own parent as well
    @PostMapping("/{id}/parent")
    public ResponseEntity<Task> setTaskParent(@RequestParam(name = "parent_id") Long parent_id, @PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        Task parent = taskRepository.findOne(parent_id);
        if(task != null || parent != null) {
            task.setParent(parent);
            parent.getChildren().add(task);
            taskRepository.save(task);
            taskRepository.save(parent);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Search routes

    @GetMapping()
    public List<Task> findByName(String name) {
        return null;
    }

}