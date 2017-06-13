package com.parking.system.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:properties/application.properties")
@SpringBootApplication(scanBasePackages={"com.parking.system.controller", "com.service.business.serviceimpl"})
@EnableJpaRepositories(basePackages = {"com.service.business.dao"})
@EnableTransactionManagement
public class AppConfig {
    
    @Resource
    Environment environment;

    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(environment.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(environment.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(environment.getRequiredProperty("db.password"));
        return new HikariDataSource(dataSourceConfig);
    }
    
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.service.domain.entity");
        
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", 
                environment.getRequiredProperty("hibernate.dialect")
        );
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                environment.getRequiredProperty("hibernate.hbm2ddl.auto")
        );
        jpaProperties.put("hibernate.ejb.naming_strategy", 
                environment.getRequiredProperty("hibernate.ejb.naming_strategy")
        );
        jpaProperties.put("hibernate.show_sql", 
                environment.getRequiredProperty("hibernate.show_sql")
        );
        jpaProperties.put("hibernate.format_sql", 
                environment.getRequiredProperty("hibernate.format_sql")
        );
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }
    
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}