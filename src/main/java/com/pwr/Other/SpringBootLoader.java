package com.pwr.Other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.pwr.NewInterface.AspectLogging;

@Configuration
@ComponentScan(basePackages = "com.pwr.")
@PropertySource("classpath:properties.properties")
@EnableAspectJAutoProxy
public class SpringBootLoader {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public static AspectLogging aspectLogging() {
		return new AspectLogging();
	}

}
