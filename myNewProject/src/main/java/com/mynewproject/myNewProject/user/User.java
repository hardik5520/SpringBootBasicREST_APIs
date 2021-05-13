package com.mynewproject.myNewProject.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

//@JsonIgnoreProperties(value={"name"})-method2 this will filter our name in response
@ApiModel
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should be of min 2 characters")
	private String name;
	
	//@JsonIgnore-this does not give value of birthDate in response--method1
	@ApiModelProperty(notes="Birth date should be in past")
	@Past
	private Date birthDate;
	
	//this default constructor is not actually needed
	//only here if in case you get an error
	//in earlier version of java it needed a default const.
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts; 
	
	protected User() {
		
	}
	public User(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	


}
