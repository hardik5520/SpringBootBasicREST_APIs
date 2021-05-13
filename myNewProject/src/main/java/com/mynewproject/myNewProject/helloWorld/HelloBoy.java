package com.mynewproject.myNewProject.helloWorld;

public class HelloBoy {
	
	private String message;

	public HelloBoy(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloBoy [message=" + message + "]";
	}
	

}
