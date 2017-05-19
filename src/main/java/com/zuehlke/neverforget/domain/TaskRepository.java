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

import java.time.LocalDate;
import java.util.List;


//XXX @PreAuthorize("hasRole('ROLE_BASIC') and principal.user.id == #id")
@RepositoryRestResource(collectionResourceRel = "task", path = "tasks") //XXX what does collectionResourceRel
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    //XXX @PostFilter("filterObject.owner == authentication.name")
    List<Task> findByName(@Param("name") String name);

    List<Task> findByCategory_Id(@Param("id") Long id);

    List<Task> findByDueDateAfterAndDueDateBefore(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after,
                                                  @Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    List<Task> findByDueDateAfter(@Param("after") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate after);

    List<Task> findByDueDateBefore(@Param("before") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate before);

    List<Task> findByDueDateIsNull();

//    @Override
//    public <T> save(T t);
//
//    @Override
//    public <T> Iterable<T> save(Iterable<T> iterable);
//
    @Override
    @PostAuthorize("returnObject.owner.username == principal.username")
    Task findOne(Long aLong);

    @Override
    boolean exists(Long id);

    @Override
    @Query("select t from Task t where t.id = ?#{principal.id}") //XXX doesnt work...
    Iterable<Task> findAll();
//
//    @Override
//    public Iterable<Task> findAll(Iterable<Long> iterable);
//
//    @Override
//    public long count();
//
//    @Override
//    public void delete(Long aLong);
//
//    @Override
//    public void delete(Task task);
//
//    @Override
//    public void delete(Iterable<? extends Task> iterable);
//
//    @Override
//    public void deleteAll();
//
//    @Override
//    public Iterable<Task> findAll(Sort sort);
//
//    @Override
//    public Page<Task> findAll(Pageable pageable);




}
