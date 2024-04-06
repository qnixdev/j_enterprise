package com.task_management_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class PostgresConfig {
    @Value("${app.pg_user}")
    private String user;

    @Value("${app.pg_password}")
    private String password;

    @Value("${app.pg_host}")
    private String host;

    @Value("${app.pg_port}")
    private String port;

    @Value("${app.pg_database}")
    private String database;

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(
            String.format("jdbc:postgresql://%1$s:%2$s/%3$s", this.host, this.port, this.database),
            this.user,
            this.password
        );
    }
}