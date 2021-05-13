/*this is a similar class to UserResources but uses JPA
 and H2 rather than hard coded date */

package com.mynewproject.myNewProject.user;

import java.awt.desktop.UserSessionEvent;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResources {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/JPA/users")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/JPA/users/{id}")
	public EntityModel<User> findOneUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id= "+id);
		
		//HATEOAS
		
		EntityModel<User> entityModel = new EntityModel(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(linkTo.withRel("all users"));
		//this is complete working of HATEOAS
		//we have created a link for all users in our response
		return entityModel;
	}
	
	@PostMapping("/JPA/users")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		User savedUser=userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/JPA/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/JPA/users/{id}/posts")
	public List<Post> retreivePost(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id="+id);
		return user.get().getPosts();
	}
	
	@PostMapping("/JPA/users/{id}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post,@PathVariable int id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id="+id);
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
//		
//		return "CREATED";
	}

}
