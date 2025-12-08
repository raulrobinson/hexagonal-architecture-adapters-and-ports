package com.demo.sd.sn.config.persistence;

import com.demo.sd.sn.infrastructure.db.InMemoryUserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories(basePackages = "com.demo.sd.sn.infrastructure")
//@EntityScan(basePackages = "com.example.customer.infrastructure.db.jpa")
public class PersistenceConfig {

    @Bean
    public InMemoryUserRepositoryAdapter outUserRepositoryPort() {
        return new InMemoryUserRepositoryAdapter();
    }
}
