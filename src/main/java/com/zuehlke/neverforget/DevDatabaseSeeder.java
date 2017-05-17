package com.zuehlke.neverforget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Component
public class DevDatabaseSeeder {
    private final static Logger log = LoggerFactory.getLogger(DevDatabaseSeeder.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostConstruct
    public void populateSampleData() {

        LocalDate parsedDate = LocalDate.parse("2017-05-17");
        log.error("------------------>" + parsedDate.getEra());
        //TODO Seeder should not be running when testing!!!
//        Task t1 = new Task("Buy Milk","",LocalDateTime.now(), false);
//        Task t2 = new Task("Build House","",LocalDateTime.now(), true);
//        Task t3 = new Task("Go to Work","",LocalDateTime.now(), false);
//        Task t4 = new Task("Write Software","",LocalDateTime.now(), false);
//
//        taskRepository.save(t1);
//        taskRepository.save(t2);
//        taskRepository.save(t3);
//        taskRepository.save(t4);
//
//        Category c = new Category("Category1", "Everything");
//        categoryRepository.save(c);
//
//        // Save via task
//        t1.setCategory(c);
//        taskRepository.save(t1);
//
//        // Save via category
//        c.getTasks().add(t1);
//        categoryRepository.save(c);
//
//        log.info("---------------------");
//        log.info("Tasks #1 has category: " + taskRepository.findOne(1L).getCategory());
//        log.info("Category #1 has " + categoryRepository.findOne(1L).getTasks().size() + " tasks: " + c.getTasks().toString());
//        log.info("---------------------");
//        log.info("Populated DB with sample data");
    }

}
