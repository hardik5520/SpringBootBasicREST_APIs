package com.mynewproject.myNewProject.user;

import java.util.*;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users =new ArrayList<>();
	private Integer userCount=3;
	static {
		users.add(new User(1, "hardik", new Date()));
		users.add(new User(2, "hitesh", new Date()));
		users.add(new User(3, "mayank", new Date()));
		
	}
	
	//method to return list of all users
	public List<User> findAll() {
		return users;
	}
	
	//method to add a user
	public User save(User user)
	{
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	//method to find a user with a userId
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}

		}
		return null;
	}
	
	//method to delete a user with a userId
		public User deleteById(int id) {
			
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext())
			{
				User user=iterator.next();
				if(user.getId()==id)
				{
					iterator.remove();
					return user;
				}
			}
			return null;
		}
	
}
