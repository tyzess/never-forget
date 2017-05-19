package com.zuehlke.neverforget.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameEquals(@Param("username") String username);
    List<User> findByUsername(@Param("username") String username);

    @Override
    @PreAuthorize("hasRole('ROLE_BASIC')")
    Page<User> findAll(Pageable pageable);

    @Override
    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    User findOne(Long aLong);
}
