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
  basePackages = "com.example.demo.repository.enviropi",
  entityManagerFactoryRef = "enviropiEntityManager",
  transactionManagerRef = "enviropiTransactionManager"
)
public class EnviropiConfig {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.enviropi")
  public DataSourceProperties enviropiProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @Autowired
  public DataSource enviropiDataSource(@Qualifier("enviropiProperties")
    DataSourceProperties properties) {
      return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @Autowired
  public LocalContainerEntityManagerFactoryBean enviropiEntityManager(EntityManagerFactoryBuilder builder,@Qualifier("enviropiDataSource") DataSource dataSource){
    return builder.dataSource(dataSource)
      .packages("com.example.demo.entity.enviropi")
      .persistenceUnit("enviropi")
      .build();
  }

  @Bean
  @Autowired
  public JpaTransactionManager enviropiTransactionManager(@Qualifier("enviropiEntityManager") LocalContainerEntityManagerFactoryBean enviropiEntityManager) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(enviropiEntityManager.getObject());
    return transactionManager;
  }
}