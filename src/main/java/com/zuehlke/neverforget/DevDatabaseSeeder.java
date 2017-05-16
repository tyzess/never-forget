package com.zuehlke.neverforget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by urzy on 16.05.2017.
 */
@Component
public class DevDatabaseSeeder {
    private final static Logger log = LoggerFactory.getLogger(DevDatabaseSeeder.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostConstruct
    public void populateSampleData() {
        Task t1 = new Task("Buy Milk","",LocalDateTime.now(), false);
        Task t2 = new Task("Build House","",LocalDateTime.now(), true);
        Task t3 = new Task("Go to Work","",LocalDateTime.now(), false);
        Task t4 = new Task("Write Software","",LocalDateTime.now(), false);

        taskRepository.save(t1);
        taskRepository.save(t2);
        taskRepository.save(t3);
        taskRepository.save(t4);

        log.info("---------------------");
        Category c = new Category("Category1", "Everything");
        categoryRepository.save(c);
        c.setTasks(new ArrayList<Task>(){{add(t1);}});
        c.getTasks().add(t2);
        categoryRepository.save(c);
        log.info("---------------------");

        log.info("Populated DB with sample data");
    }

}