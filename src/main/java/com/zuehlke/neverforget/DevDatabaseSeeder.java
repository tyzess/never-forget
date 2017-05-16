package com.zuehlke.neverforget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by urzy on 16.05.2017.
 */
@Component
public class DevDatabaseSeeder {
    private final static Logger log = LoggerFactory.getLogger(DevDatabaseSeeder.class);

    @Autowired
    TaskRepository taskRepository;

    @PostConstruct
    public void populateSampleData() {
        taskRepository.save(new Task("Buy Milk","",LocalDateTime.now(), false));
        taskRepository.save(new Task("Build House","",LocalDateTime.now(), true));
        taskRepository.save(new Task("Go to Work","",LocalDateTime.now(), false));
        taskRepository.save(new Task("Write Software","",LocalDateTime.now(), false));

        log.info("Populated DB with sample data");
    }

}