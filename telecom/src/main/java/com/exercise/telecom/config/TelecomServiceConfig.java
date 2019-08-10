package com.exercise.telecom.config;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A Tissue Configuration MicroService Application configuration.
 * @author jafferj
 */
@Configuration
@EnableTransactionManagement
@EntityScan({ "com.exercise.telecom.entity" })
@Import(AppsCommonConfig.class)
@PropertySource(value = { "classpath:telecom-persistence-config.properties",
  "classpath:telecom-application.properties" })
public class TelecomServiceConfig {

  @Autowired
  private DozerBeanMapper mapper;

  /**
   * Setup the local bean-mappings XML configuration to the Dozer-mapper bean
   */
  @PostConstruct
  public void afterPropertiesSet() {
    mapper.addMapping(
      TelecomServiceConfig.class.getResourceAsStream( "/telecom-dozer-mapping.xml" ) );
  }
}
