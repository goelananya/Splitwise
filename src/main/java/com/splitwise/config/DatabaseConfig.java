package com.splitwise.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Primary
public class DatabaseConfig {
    @Bean(name = "aws")
    public DataSource awsDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://splitwise-itf.cf265tfrj3n0.us-east-2.rds.amazonaws.com:3306/defDB");
        dataSourceBuilder.username(System.getProperty("dbUser"));
        dataSourceBuilder.password(System.getProperty("dbPass"));
        return dataSourceBuilder.build();
    }
}
