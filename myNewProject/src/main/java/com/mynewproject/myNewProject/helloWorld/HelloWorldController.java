package com.mynewproject.myNewProject.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET, path="/hello-world")
	public String helloWorld() {
		return "HELLO-WORLD";
	}
	
	//get mapping to return a beam
	@GetMapping( path="/hello-boy")
	public HelloBoy helloboy() {
		return new HelloBoy("hello-boy");
	}
	
	//passing arguments in url and displaying it
	@GetMapping( path="/hello-boy/pathvariable/{name}")
	public HelloBoy helloboypathvariable(@PathVariable String name) {
		return new HelloBoy("hello-boy- "+name);
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping( path="/hello-boy-internationalised")
//	public String helloboyinternationalised(@RequestHeader(name="Accept-Language", required = false) Locale locale) { or -
	public String helloboyinternationalised() {
		//return messageSource.getMessage("good.morning.message", null, locale); or
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()); 
		
	}
	
	
}
