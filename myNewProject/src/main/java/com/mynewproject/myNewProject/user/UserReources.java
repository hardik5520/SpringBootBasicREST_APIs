package com.mynewproject.myNewProject.user;

import java.awt.desktop.UserSessionEvent;
import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserReources {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> findOneUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id= "+id);
		
		//HATEOAS
		
		EntityModel<User> entityModel = new EntityModel(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(linkTo.withRel("all users"));
		//this is complete working of HATEOAS
		//we have created a link for all users in our response
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		User savedUser=service.save(user);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id= "+id);
	}

}
