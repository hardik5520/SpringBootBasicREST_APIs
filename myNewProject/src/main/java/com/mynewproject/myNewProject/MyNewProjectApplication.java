package com.mynewproject.myNewProject;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import antlr.debug.NewLineEvent;

@SpringBootApplication
public class MyNewProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNewProjectApplication.class, args);
	}
	
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver =new SessionLocaleResolver(); in this return 
//		should be of type sessionlocaleresolver
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver(); 
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
//		messageSource.setBasenames("messages");
//		return messageSource;
//	}
	
	/*the above code is not needed as we have defined
	 * spring.messages.basename=messages
	 * in application.properties
	 */
	
	

}
