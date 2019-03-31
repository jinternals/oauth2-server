package com.jniternals.cloud.example.authserver.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import static org.springframework.boot.jdbc.DataSourceBuilder.create;

@Configuration
@EnableConfigurationProperties(AuthServerProperties.class)
public class AuthServerConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return create().type(HikariDataSource.class).build();

    }

}
