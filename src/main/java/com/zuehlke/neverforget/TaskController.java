package com.zuehlke.neverforget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by urzy on 15.05.2017.
 */
//@RestController
//@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    //@Autowired

    private TaskRepository taskRepository;

    @RequestMapping(path = "/add", method = POST)
    public String addNewTask (@RequestParam String name, @RequestParam String description) {
        Task n = new Task();
        n.setName(name);
        n.setDescription(description);
        n.setDueDatetime(LocalDateTime.now());
        n.setWholeDay(false);
        taskRepository.save(n);
        return "Saved";
    }

    @RequestMapping(path = "/all", method = GET)
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @RequestMapping(path = "/delete", method = DELETE)
    public ResponseEntity<Task> deleteTask(Long id) {
        if (taskRepository.exists(id)) {
            taskRepository.delete(id);
            return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping(path = "/all")
//    public Iterable<Task> getTasksByDueDatetime(@RequestParam LocalDateTime dueDatetime) {
//        return taskRepository.findByDueDatetime(dueDatetime);
//    }
}