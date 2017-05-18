package com.zuehlke.neverforget;

import com.zuehlke.neverforget.model.Category;
import com.zuehlke.neverforget.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@RequestMapping(path = "/tasks")
public class TaskController {
    private final static Logger log = LoggerFactory.getLogger(DevDatabaseSeeder.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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

    //XXX pass category id via body params
    @RequestMapping(path = "/{id}/category", method = POST)
    public @ResponseBody ResponseEntity<Task> setTaskCategory(@RequestParam(name = "category_id") Long category_id, @PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        Category category = categoryRepository.findOne(category_id);
        if(task != null) {
            task.setCategory(category);
            taskRepository.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(path = "/{id}/category", method = GET)
//    public @ResponseBody ResponseEntity<Category> getTaskCategory(@PathVariable Long id) {
//        Task task = taskRepository.findOne(id);
//        if(task != null) {
//            Category category = task.getCategory();
//            return new ResponseEntity<>(category, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @RequestMapping(path = "/{id}/parent", method = GET)
//    public @ResponseBody ResponseEntity<Task> getTaskParent(@PathVariable Long id) {
//        Task task = taskRepository.findOne(id);
//        if(task != null) {
//            Task parent = task.getParent();
//            return new ResponseEntity<>(parent, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}