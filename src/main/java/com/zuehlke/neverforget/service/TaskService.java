package com.zuehlke.neverforget.service;

import com.zuehlke.neverforget.domain.Task;
import com.zuehlke.neverforget.domain.TaskRepository;
import com.zuehlke.neverforget.domain.User;
import com.zuehlke.neverforget.controller.TaskController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @PostAuthorize("returnObject == null or returnObject.owner.username == principal.username")
    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }

    public void createTask(Task task) {
        task.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        taskRepository.save(task);
    }

    @PostAuthorize("returnObject.owner.username == principal.username")
    public Task updateTask(Long id, Task task) {
        Task currentTask = taskRepository.findOne(id);
        currentTask.setName(task.getName());
        currentTask.setDueDate(task.getDueDate());
        currentTask.setDueTime(task.getDueTime());
        currentTask.setWholeDay(task.isWholeDay());
        currentTask.setCategory(task.getCategory());
        taskRepository.save(currentTask);
        return currentTask;
    }

    public void deleteTask(Long id) {
        List<Task> tasks = taskRepository.findAllByParent_Id(id);
        for(Task task : tasks)
            task.setParent(null);
        taskRepository.delete(id);
    }
}
