package com.zuehlke.neverforget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by urzy on 15.05.2017.
 */
@Controller
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;


    @RequestMapping(path = "/{id}/check", method = POST)
    public @ResponseBody ResponseEntity<Task> checkTask(@PathVariable Long id) {
        Task t = taskRepository.findOne(id);
        if(t != null) {
            t.setChecked(true);
            taskRepository.save(t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}/uncheck", method = POST)
    public @ResponseBody ResponseEntity<Task> uncheckTask(@PathVariable Long id) {
        Task t = taskRepository.findOne(id);
        if(t != null) {
            t.setChecked(false);
            taskRepository.save(t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}