package com.zuehlke.neverforget.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


//XXX @PreAuthorize("hasRole('ROLE_BASIC') and principal.user.id == #id")
@Repository()
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByName(@Param("name") String name);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByCategory_Id(@Param("id") Long id);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByDueDateAfterAndDueDateBefore(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after,
                                                  @Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByDueDateAfter(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByDueDateBefore(@Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findByDueDateIsNull();

    List<Task> findAllByParent_Id(@Param("parent_id") Long parent_id);

}
