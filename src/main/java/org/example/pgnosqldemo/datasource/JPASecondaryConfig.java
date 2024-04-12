package org.example.pgnosqldemo.datasource;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Ocean
 * @date 2023/8/14 9:49
 * @description JPASecondaryConfig
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = JPASecondaryConfig.entityManagerFactorySecondary,
//        transactionManagerRef = JPASecondaryConfig.transactionManagerSecondary,
//        basePackages = {JPASecondaryConfig.basePackages}    // 第一个数据源的 repository包 所在位置
//)
public class JPASecondaryConfig {

    public static final String secondaryPersistenceUnit = "secondaryPersistenceUnit";
    public static final String transactionManagerSecondary = "transactionManagerSecondary";
    protected static final String basePackages = "org.example.pgnosqldemo";
    protected static final String entityPackages = "org.example.pgnosqldemo";
    protected static final String entityManagerFactorySecondary = "entityManagerFactorySecondary";

    private static final String mysqlDialect = "org.hibernate.dialect.MySQLDialect";

    @Resource
    private JpaProperties jpaProperties;
    @Resource
    private HibernateProperties hibernateProperties;

    @Resource
    @Qualifier(HikariDataSourceConfig.secondDataSource)
    private DataSource secondDataSource;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = entityManagerFactorySecondary)
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder){
        Map<String , Object> properties =
                hibernateProperties.determineHibernateProperties(
                        jpaProperties.getProperties(),
                        new HibernateSettings()
                );
        return builder.dataSource(secondDataSource)
                .properties(properties)
                .packages(entityPackages)    // 第 1 个数据源的 domain实体类包 所在位置
                .persistenceUnit(secondaryPersistenceUnit)
                .build();
    }

    @Bean(name = transactionManagerSecondary)
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
