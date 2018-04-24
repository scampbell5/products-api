package com.sean.productsapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Profile("h2")
    public DataSource dataSource(@Value("${spring.h2.username}") String userName,
                                 @Value("${spring.h2.password}") String password) {
        return new DriverManagerDataSource("jdbc:h2:mem:productsdb;DB_CLOSE_DELAY=-1", userName, password);
    }
}
