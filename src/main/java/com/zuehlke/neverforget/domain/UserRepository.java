package com.zuehlke.neverforget.domain;

import com.zuehlke.neverforget.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameEquals(@Param("username") String username); //XXX Security issue!
}
