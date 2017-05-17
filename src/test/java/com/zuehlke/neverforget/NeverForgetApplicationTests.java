package com.zuehlke.neverforget;

import com.zuehlke.neverforget.model.Category;
import com.zuehlke.neverforget.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NeverForgetApplicationTests {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void taskIsSaved() {
		Task task = new Task("test1", "desc",  LocalDate.now(), LocalTime.now(), false);
		assertThat(taskRepository.findOne(1L), is(nullValue()));
		taskRepository.save(task);
		assertEquals(taskRepository.findOne(1L).getName(), task.getName());
		assertEquals(taskRepository.findOne(1L).getDescription(), task.getDescription());
		assertEquals(taskRepository.findOne(1L).getDueDate(), task.getDueDate());
	}

	@Test
	public void categoryIsSaved() {
		Category category = new Category("test1", "desc");
		assertThat(categoryRepository.findOne(1L), is(nullValue()));
		categoryRepository.save(category);
		assertEquals(categoryRepository.findOne(1L).getName(), category.getName());
		assertEquals(categoryRepository.findOne(1L).getDescription(), category.getDescription());
	}

	@Test
	public void categoryCanBeAddedToTask() {
		Task task = new Task("test1", "desc", LocalDate.now(), LocalTime.now(), false);
		taskRepository.save(task);
		Category category = new Category("test1", "desc");
		categoryRepository.save(category);

		assertThat(taskRepository.findOne(1L).getCategory(), nullValue());

		task.setCategory(category);
		taskRepository.save(task);

		assertEquals(taskRepository.findOne(1L).getCategory(), categoryRepository.findOne(1L));
	}

}
