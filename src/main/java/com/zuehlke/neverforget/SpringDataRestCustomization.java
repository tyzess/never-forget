package com.zuehlke.neverforget;


import com.zuehlke.neverforget.domain.Category;
import com.zuehlke.neverforget.domain.Task;
import com.zuehlke.neverforget.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Task.class, Category.class, User.class);
    }
}
