package com.zuehlke.neverforget.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
