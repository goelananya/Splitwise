package com.splitwise.config;

import com.splitwise.util.SplitWiseConstants;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration

public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource awsDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(SplitWiseConstants.AWS_DB_URL);
        dataSourceBuilder.username(System.getenv(SplitWiseConstants.DB_USER_PARAM_KEY));
        dataSourceBuilder.password(System.getenv(SplitWiseConstants.DB_PASSWORD_PARAM_KEY));
        return dataSourceBuilder.build();
    }
}