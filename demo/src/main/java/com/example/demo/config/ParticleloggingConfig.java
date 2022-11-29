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
  basePackages = "com.example.demo.repository.particlelogging",
  entityManagerFactoryRef = "particleloggingEntityManager",
  transactionManagerRef = "particleloggingTransactionManager"
)
public class ParticleloggingConfig {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.particlelogging")
  public DataSourceProperties particleloggingProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @Autowired
  public DataSource particleloggingDataSource(@Qualifier("particleloggingProperties")
    DataSourceProperties properties) {
      return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @Autowired
  public LocalContainerEntityManagerFactoryBean particleloggingEntityManager(EntityManagerFactoryBuilder builder,@Qualifier("particleloggingDataSource") DataSource dataSource){
    return builder.dataSource(dataSource)
      .packages("com.example.demo.entity.particlelogging")
      .persistenceUnit("particlelogging")
      .build();
  }

  @Bean
  @Autowired
  public JpaTransactionManager particleloggingTransactionManager(@Qualifier("particleloggingEntityManager") LocalContainerEntityManagerFactoryBean particleloggingEntityManager) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(particleloggingEntityManager.getObject());
    return transactionManager;
  }
}