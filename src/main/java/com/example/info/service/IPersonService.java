package com.example.info.service;

import java.util.List;

import com.example.info.dto.PersonDTO;
import com.example.info.model.Person;

public interface IPersonService {
	
	public Person save(Person person); 
	
	public List<Person> findAll(); 
	
	public Person findByDni(String dni); 
	
	public List<Person> findByLastname(String last_name); 
	
	public Person Compare_key(Person person, PersonDTO persondto); 
	
	public Person updatePerson(Person persona, PersonDTO person); 
	
	public Person deletePerson(Person person); 

}
