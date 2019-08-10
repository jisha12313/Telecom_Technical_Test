package com.exercise.telecom.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This is the common Java based configuration for:
 * 1. JPA Persistence  
 * 2. Logger
 * 3. Swagger-REST API Documentation tool
 * 4. Dozer Bean Mapper
 * 
 * @author ahirer
 * @see WebMvcConfigurer
 */
@Configuration
@ComponentScan
public class AppsCommonConfig
  implements
  WebMvcConfigurer {

  /**
   * Define a bean for DozerBeanMapper
   * 
   * @return mapper
   * A bean of DozerBeanMapper
   * @see DozerBeanMapper
   * @throws Exception
   * The exception thrown if any
   */
  @Bean
  public DozerBeanMapper mapper()
    throws Exception {
    return new DozerBeanMapper();
  }
  
}