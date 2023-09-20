//package com.smartcode.web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.mapping.JpaPersistentProperty;
//import org.springframework.data.mapping.model.Property;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
//@Configuration
//public class ApplicationConfiguration {
//
//
//    @Bean
//    public DataSource dataSource() {
//        SingleConnectionDataSource singleConnectionDataSource = new SingleConnectionDataSource();
//        singleConnectionDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
//        singleConnectionDataSource.setSuppressClose(true);
//        singleConnectionDataSource.setUsername("postgres");
//        singleConnectionDataSource.setPassword("postgres");
//        singleConnectionDataSource.setDriverClassName("org.postgres.Driver");
//        return singleConnectionDataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource());
//        entityManagerFactory.setPackagesToScan("com.smartcode.web.model");
//        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
//        entityManagerFactory.setJpaProperties(additionalProperties());
//        return entityManagerFactory;
//    }
//
//    @Bean
//    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
//        return new HibernateJpaVendorAdapter();
//    }
//
//    @Bean
//    public Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//
//        return properties;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//
//
//}