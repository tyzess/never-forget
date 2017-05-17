package com.zuehlke.neverforget;

import com.zuehlke.neverforget.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findByName(@Param("name") String name);
}
