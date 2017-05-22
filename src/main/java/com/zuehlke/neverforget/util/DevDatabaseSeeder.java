package com.zuehlke.neverforget.util;

import com.zuehlke.neverforget.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


@Component
public class DevDatabaseSeeder {
    private final static Logger log = LoggerFactory.getLogger(DevDatabaseSeeder.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void populateSampleData() { //TODO Seeder should not be running when testing!!!

        User u1 = new User("urs", "123", "123");
        User u2 = new User("hans", "123", "123");
        userRepository.save(u1);
        userRepository.save(u2);

        Task parent   = new Task("papi", "", LocalDate.now(), LocalTime.now(), false, u1);
        Task son      = new Task("son", "", LocalDate.now(), LocalTime.now(), false, u1);
        Task daughter = new Task("daughter", "", LocalDate.now(), LocalTime.now(), false, u2);

        son.setParent(parent);
        daughter.setParent(parent);
        parent.setChildren(Arrays.asList(son, daughter));

        taskRepository.save(parent);
        taskRepository.save(son);
        taskRepository.save(daughter);

        Category c = new Category("Category1", "Everything", u1);
        categoryRepository.save(c);

        son.setCategory(c);
        taskRepository.save(son);

//        log.info("Father is: " + taskRepository.findOne(parent.getId()));
//        log.info("Son is: " + taskRepository.findOne(son.getId()));
//        log.info("Daughter is: " + taskRepository.findOne(daughter.getId()));


    }

}
