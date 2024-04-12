package org.example.pgnosqldemo.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Ocean
 * @date 2024/4/12 10:21
 * @description HikariDataSourceConfiguration
 */
@Configuration
public class HikariDataSourceConfig {

    protected static final String primaryDataSource = "primaryDataSource";

    protected static final String secondDataSource = "secondDataSource";

    /**
    * @Author : Ocean
    * @createTime : 2024/4/12 10:53
    * @Description ： datasource properties
    */
    @Primary
    @Bean("primaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("secondaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
    * @Author : Ocean
    * @createTime : 2024/4/12 10:55
    * @Description ： init hikali properity
    */
    @Primary
    @Bean(primaryDataSource)
    @ConfigurationProperties(prefix = "spring.datasource.primary.hikari")
    public DataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

//    @Bean(secondDataSource)
//    @ConfigurationProperties(prefix = "spring.datasource.secondary.hikari")
//    public DataSource secondaryDataSource() {
//        return secondaryDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }

}
