package com.mynewproject.myNewProject.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	//method-1->
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Hardik Arora");
	}
	
	@GetMapping("v2/person")
	public Personv2 personV2() {
		return new Personv2(new Name("Hardik","arora"));
	}
	
	//method-2->
	//url=http://localhost:8080/person/param?version=1
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 pqramV1() {
		return new PersonV1("Hardik Arora");
	}
	
	@GetMapping(value="person/param", params="version=2")
	public Personv2 paramV2() {
		return new Personv2(new Name("Hardik","Arora"));
	}
	
	//method-3->
	//now pass details aboout version in header
		@GetMapping(value="/person/header", headers ="X-API-VERSION=1")
		public PersonV1 headersV1() {
			return new PersonV1("Hardik Arora");
		}
		
		@GetMapping(value="/person/header", headers="X-API-VERSION=2")
		public Personv2 headersV2() {
			return new Personv2(new Name("Hardik","Arora"));
		}
		
		//method-4->
		//now we use produces,also called content negotiation or accept versioning
			@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
			public PersonV1 producesV1() {
				return new PersonV1("Hardik Arora");
			}
			
			@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v2+json")
			public Personv2 producesV2() {
				return new Personv2(new Name("Hardik","Arora"));
			}
}
