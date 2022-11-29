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
  basePackages = "com.example.demo.repository.prd",
  entityManagerFactoryRef = "prdEntityManager",
  transactionManagerRef = "prdTransactionManager"
)
public class PrdConfig {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.prd")
  public DataSourceProperties prdProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @Autowired
  public DataSource prdDataSource(@Qualifier("prdProperties")
    DataSourceProperties properties) {
      return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @Autowired
  public LocalContainerEntityManagerFactoryBean prdEntityManager(EntityManagerFactoryBuilder builder,@Qualifier("prdDataSource") DataSource dataSource){
    return builder.dataSource(dataSource)
      .packages("com.example.demo.entity.prd")
      .persistenceUnit("prd")
      .build();
  }

  @Bean
  @Autowired
  public JpaTransactionManager prdTransactionManager(@Qualifier("prdEntityManager") LocalContainerEntityManagerFactoryBean prdEntityManager) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(prdEntityManager.getObject());
    return transactionManager;
  }
}