package com.zuehlke.neverforget.config;


import com.zuehlke.neverforget.domain.model.Category;
import com.zuehlke.neverforget.domain.model.Task;
import com.zuehlke.neverforget.domain.model.User;
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
