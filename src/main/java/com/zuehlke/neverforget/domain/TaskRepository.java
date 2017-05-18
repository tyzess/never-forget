package com.zuehlke.neverforget.domain;

import com.zuehlke.neverforget.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "task", path = "tasks") //XXX what does collectionResourceRel
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findByName(@Param("name") String name);
    List<Task> findByCategory_Id(@Param("id") Long id);
    List<Task> findByDueDateAfterAndDueDateBefore(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after,
                                                  @Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);
    List<Task> findByDueDateAfter(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after);
    List<Task> findByDueDateBefore(@Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);
    List<Task> findByDueDateIsNull();
}
