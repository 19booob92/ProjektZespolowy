package com.pwr.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import Utils.JsonDateSerializer;

import com.pwr.Editor.QuestTableView;
import com.pwr.Editor.UserDetailsView;
import com.pwr.NewInterface.AspectLogging;
import com.pwr.NewInterface.ConfirmView;
import com.pwr.NewInterface.ProjectMainView;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDataRegister;
import com.sun.research.ws.wadl.Request;

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

	@Bean
	public static ProjectMainView projectMainView() {
		return new ProjectMainView();
	}

	@Bean
	public static Requests requests() {
		return new Requests();
	}

	@Bean
	@Autowired
	public static QuestTableView questTableView(Requests requests) {
		return new QuestTableView(requests);
	}

	@Bean
	public static ConfirmView confirmView() {
		return new ConfirmView();
	}

	@Bean
	public static UserDataRegister UserDataRegister() {
		return new UserDataRegister();
	}

	@Bean
	public static CreatePDFRaport createPDFRaport() {
		return new CreatePDFRaport();
	}

	@Bean
	public static UserDetailsView userDetailsView() {
		return new UserDetailsView();
	}

	@Bean
	public static CreateHTMLRaport createHTMLRaport() {
		return new CreateHTMLRaport();
	}

	@Bean
	public static JsonDateSerializer jsonDateSerializer() {
		return new JsonDateSerializer();
	}

}
