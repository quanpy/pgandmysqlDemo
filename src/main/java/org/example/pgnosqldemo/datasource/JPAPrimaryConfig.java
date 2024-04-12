package org.example.pgnosqldemo.datasource;

/**
 * @author Ocean
 * @date 2023/8/10 11:47
 * @description JPAPrimaryConfig
 */

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * 实现Spring Data JPA 多数据源（本应用涉及到两个数据库）配置的文件。
 * 此处是主数据源的配置文件。
 * Primary指的是主数据源。
 * 若是进行二次开发，在本配置文件内，需要修改的地方已加注释。
 * 此文件需要修改两个地方。
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = JPAPrimaryConfig.entityManagerFactoryPrimary,
        transactionManagerRef = JPAPrimaryConfig.transactionManagerPrimary,
        basePackages = {"org.example.pgnosqldemo.pg"}    // 第一个数据源的 repository包 所在位置
)
public class JPAPrimaryConfig {

    public static final String primaryPersistenceUnit = "primaryPersistenceUnit";
    public static final String transactionManagerPrimary = "transactionManagerPrimary";
    protected static final String entityManagerFactoryPrimary = "entityManagerFactoryPrimary";

    @Resource
    private JpaProperties jpaProperties;
    @Resource
    private HibernateProperties hibernateProperties;

    @Resource
    @Qualifier(HikariDataSourceConfig.primaryDataSource)
    private DataSource primaryDataSource;

    private static final String postgreSQLDialect = "org.hibernate.dialect.PostgreSQLDialect";

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = entityManagerFactoryPrimary)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder){
        Map<String, String> map = new HashMap<>();
        // 设置对应的数据库方言
        map.put("hibernate.dialect", postgreSQLDialect);
        jpaProperties.setProperties(map);
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(primaryDataSource)
                .properties(properties)
                .packages("org.example.pgnosqldemo.pg")    // 第 1 个数据源的 domain实体类包 所在位置
                .persistenceUnit(primaryPersistenceUnit)
                .build();
    }

    @Primary
    @Bean(name = transactionManagerPrimary)
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}