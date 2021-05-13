package com.mynewproject.myNewProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticApplicationContext;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() { 
   	
		 final Contact DEFAULT_CONTACT
	      = new Contact(
	      "Hardik Arora",
	      "+91 8299490522",
	      "hardikarora5520@gmail.com");
		 
	  final ApiInfo DEFAULT
	      = new ApiInfo(
	      "Awesome API Title",
	      "Awesome API Descreption",
	      "1.0",
	      "urn:tos",
	      DEFAULT_CONTACT,
	      "Apache 2.0",
	      "http://www.apache.org/licenses/LICENSE-2.0",
	      new ArrayList<>());
     
		Set<String> DEFAULT_PRODUCES_AND_CONSUMES=new HashSet<String>(Arrays.asList
				("application/json","application/xml"));
		
		return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(DEFAULT)
        		.produces(DEFAULT_PRODUCES_AND_CONSUMES)
        		.consumes(DEFAULT_PRODUCES_AND_CONSUMES);                                           
    }

	
	//this bean is to configure httptrace in actuator response
	//@Bean
//	public HttpTraceRepository htttpTraceRepository()
//	{
//	  return new InMemoryHttpTraceRepository();
//	}
//	to use this go to actuator/httptrace or use httptrace command in 
	// application.propertoes file
}
