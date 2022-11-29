package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.example.demo.repository.tracking",
  entityManagerFactoryRef = "trackingEntityManager",
  transactionManagerRef = "trackingTransactionManager"
)
public class TrackingConfig {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.tracking")
  public DataSourceProperties trackingProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @Autowired
  public DataSource trackingDataSource(@Qualifier("trackingProperties")
    DataSourceProperties properties) {
      return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @Autowired
  public LocalContainerEntityManagerFactoryBean trackingEntityManager(EntityManagerFactoryBuilder builder,@Qualifier("trackingDataSource") DataSource dataSource){
    return builder.dataSource(dataSource)
      .packages("com.example.demo.entity.tracking")
      .persistenceUnit("tracking")
      .build();
  }

  @Bean
  @Autowired
  public JpaTransactionManager trackingTransactionManager(@Qualifier("trackingEntityManager") LocalContainerEntityManagerFactoryBean trackingEntityManager) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(trackingEntityManager.getObject());
    return transactionManager;
  }
}