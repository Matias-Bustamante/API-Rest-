package com.example.info.wrapper;
import com.example.info.utils.generar_key;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.example.info.dto.PersonDTO;
import com.example.info.model.Person;
import com.example.info.model.Status;

public class PersonWrapper {

	public static Person persondtoentity(PersonDTO persondto) { 
		
		String key; 
		key=UUID.randomUUID().toString().substring(0,generar_key.generar_key()); 
		Person person = new Person(); 
		
		person.setFirstname(persondto.getFirst_name().toLowerCase());
		person.setLastname(persondto.getLast_name().toLowerCase());
		person.setDni(persondto.getDni());
		person.setDate(new GregorianCalendar());
	
		person.setStatus(Status.ACTIVO); //Activado = 0  Inactivo = 1
		person.setKey_code(key);
		
		return person; 
		
	}
	
	
	public static PersonDTO entitytopersondto(Person person) { 
		
		PersonDTO persondto = new PersonDTO(); 
		persondto.setFirst_name(person.getFirstname());
		persondto.setLast_name(person.getLastname());
		persondto.setDni(person.getDni());
		
		return persondto; 
	}
}
