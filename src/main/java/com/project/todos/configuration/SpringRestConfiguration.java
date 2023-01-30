package com.project.todos.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import javax.persistence.EntityManager;

@Configuration
@EntityScan("com.project.todos.entity")
@EnableJpaRepositories("com.project.todos.repository")
public class SpringRestConfiguration implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    public SpringRestConfiguration(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}