package com.example.demo.config;

import java.util.HashMap;

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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.example.demo.repository.atlas",
  entityManagerFactoryRef = "atlasEntityManager",
  transactionManagerRef = "atlasTransactionManager"
)
public class AtlasConfig {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.atlas")
  public DataSourceProperties atlasProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @Autowired
  public DataSource atlasDataSource(@Qualifier("atlasProperties")
    DataSourceProperties properties) {
      return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @Autowired
  public LocalContainerEntityManagerFactoryBean atlasEntityManager(EntityManagerFactoryBuilder builder,@Qualifier("atlasDataSource") DataSource dataSource){
    return builder.dataSource(dataSource)
      .packages("com.example.demo.entity.atlas")
      .persistenceUnit("atlas")
      .build();
  }

  @Bean
  @Primary
  @Autowired
  public JpaTransactionManager atlasTransactionManager(@Qualifier("atlasEntityManager") LocalContainerEntityManagerFactoryBean atlasEntityManager) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(atlasEntityManager.getObject());
    return transactionManager;
  }
  
  @Bean
  @Primary
  public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
     return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
  }
}