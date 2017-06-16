package com.zuehlke.neverforget.domain;

import com.zuehlke.neverforget.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@Repository
//@RepositoryRestResource(exported = false)
public interface TaskRepository extends JpaRepository<Task, Long> {

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByParentIsNull();

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByName(@Param("name") String name);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByCategory_Id(@Param("id") Long id);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByDueDateAfterAndDueDateBefore(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after,
                                                     @Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByDueDateAfter(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByDueDateBefore(@Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    @PostFilter("filterObject.owner.username == authentication.principal.username")
    List<Task> findAllByDueDateIsNull();

    List<Task> findAllByParent_Id(@Param("parent_id") Long parent_id);

}
