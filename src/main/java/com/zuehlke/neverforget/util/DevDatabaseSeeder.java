package com.zuehlke.neverforget.util;

import com.zuehlke.neverforget.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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


        // Create users

        User u1 = new User("urs", "123", "123");
        User u2 = new User("guest", "123", "123");

        userRepository.save(u1);
        userRepository.save(u2);


        // Create categories

        Category c1 = new Category("Shopping", "Buying stuff is great!", u1);
        Category c2 = new Category("Fitness", "", u1);

        categoryRepository.save(c1);
        categoryRepository.save(c2);


        // Create tasks

        List<Task> tasks = new ArrayList<>();
        Task t1, t1s1, t1s2, t1s3;
        Task t2, t2s1;
        Task t3, t3s1;
        Task t4;
        Task t5;

        tasks.add(t1 = new Task("Groceries", "", LocalDate.now(), LocalTime.now().plusHours(2L), false, u1));
        tasks.add(t1s1 = new Task("Buy Milk", "", null, null, false, u1));
        tasks.add(t1s2 = new Task("Buy Chocolate", "", null, null, false, u1));
        tasks.add(t1s3 = new Task("Buy Chocolate", "", null, null, false, u1));

        t1.setCategory(c1);
        t1s1.setParent(t1);
        t1s2.setParent(t1);
        t1s3.setParent(t1);

        tasks.add(t2 = new Task("Lose some weight", "", LocalDate.now().plusDays(5L), LocalTime.NOON, false, u1));
        tasks.add(t2s1 = new Task("Go running", "", null, null, false, u1));

        t2.setCategory(c2);
        t2s1.setParent(t2);

        tasks.add(t3 = new Task("Buy new Clothes", "", LocalDate.now().plusDays(5L), LocalTime.NOON, false, u1));
        tasks.add(t3s1 = new Task("4 T-Shirts", "", null, null, false, u1));

        t3.setCategory(c1);
        t3s1.setParent(t3);

        tasks.add(t4 = new Task("Talk with Mr. Trump", "", LocalDate.now().plusDays(5L), LocalTime.NOON, false, u1));

        tasks.add(t5 = new Task("Not my Task ;)", "", LocalDate.now().plusDays(5L), LocalTime.NOON, false, u2));

        for(Task task : tasks)
            taskRepository.save(task);
    }

}
