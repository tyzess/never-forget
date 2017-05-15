package com.zuehlke.neverforget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by urzy on 15.05.2017.
 */
@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired

    private TaskRepository taskRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewTask (@RequestParam String name, @RequestParam String description) {
        Task n = new Task();
        n.setName(name);
        n.setDescription(description);
        n.setDueDatetime(LocalDateTime.now());
        n.setWholeDay(false);
        taskRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}