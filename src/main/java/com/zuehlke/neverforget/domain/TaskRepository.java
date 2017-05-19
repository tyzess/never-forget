package com.zuehlke.neverforget.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.List;


//XXX @PreAuthorize("hasRole('ROLE_BASIC') and principal.user.id == #id")
@RepositoryRestResource(collectionResourceRel = "task", path = "tasks") //XXX what does collectionResourceRel
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByName(@Param("name") String name);

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByCategory_Id(@Param("id") Long id);

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByDueDateAfterAndDueDateBefore(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after,
                                                  @Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByDueDateAfter(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after);

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByDueDateBefore(@Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByDueDateIsNull();
}
