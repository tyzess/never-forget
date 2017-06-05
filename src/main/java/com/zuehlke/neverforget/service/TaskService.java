package com.zuehlke.neverforget.service;

import com.zuehlke.neverforget.domain.Category;
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
    public List<Task> findAll() {
        return taskRepository.findAllByParentIsNull();
    }

    @PostAuthorize("returnObject == null or returnObject.owner.username == principal.username")
    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }

    public Task createTask(Task task) {
        task.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        task.setCategory(null);
        return taskRepository.save(task);
    }

    @PostAuthorize("returnObject.owner.username == principal.username")
    public Task updateTask(Long id, Task task) {
        Task currentTask = taskRepository.findOne(id);
        currentTask.setName(task.getName());
        currentTask.setDueDate(task.getDueDate());
        currentTask.setDueTime(task.getDueTime());
        currentTask.setWholeDay(task.isWholeDay());
        return taskRepository.save(currentTask);
    }

    // TODO Check that all removed tasks are owned by current user
    public void deleteTask(Long id) {
        List<Task> children = taskRepository.findAllByParent_Id(id);
        for (Task child : children) {
            deleteId(child.getId());
            deleteTask(child.getId());
        }
        deleteId(id);
    }

    private void deleteId(Long id) {
        if (taskRepository.exists(id))
            taskRepository.delete(id);
    }

    // TODO Check that only owner can change checked
    public Task setChecked(Task task, boolean checked) {
        task.setChecked(checked);
        return taskRepository.save(task);
    }

    // TODO Check that only owner can set the category
    // TODO Check that category is owned by current user
    public Task setCategory(Task task, Category category) {
        task.setCategory(category);
        return taskRepository.save(task);
    }

    // TODO same as with setCategroy(...)
    public Task setParent(Task task, Task parent) {
        task.setParent(parent);
        //parent.getChildren().add(task);
        //taskRepository.save(parent);
        return taskRepository.save(task);
    }

    // TODO Check ownership
    public List<Task> getTaskChildren(Long id) {
        return taskRepository.findAllByParent_Id(id);
    }

}
