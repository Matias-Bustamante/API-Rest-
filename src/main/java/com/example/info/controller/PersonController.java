package com.example.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.info.dto.PersonDTO;
import com.example.info.model.Person;
import com.example.info.model.Status;
import com.example.info.service.IPersonService;
import com.example.info.service.PersonServiceImp;
import com.example.info.wrapper.PersonWrapper;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
	
	@Autowired
	private IPersonService personService; 
	
	@PostMapping //guarda una persona
	public ResponseEntity<HashMap<String, Object>> createPerson(@RequestBody PersonDTO persondto) { 
		
		HashMap<String, Object> response = new HashMap<>(); 
		
		Person person=PersonWrapper.persondtoentity(persondto); 
		personService.save(person); 
		persondto.setKey_code("Protegido");
		response.put("Persona", persondto); 
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@GetMapping("") //obtiene todas las personas activas
	public ResponseEntity<HashMap<String, Object>> allPerson() { 
		
		HashMap<String, Object> response=new HashMap<>(); 
		
		List<Person> person=personService.findAll(); 
		
		List<PersonDTO> person_dto=new ArrayList(); 
		
		for (int i=0; i<person.size(); i++) 
		{ 
			person_dto.add(PersonWrapper.entitytopersondto(person.get(i))); 
			person_dto.get(i).setKey_code("Protegido");
		}
		
		response.put("Persona", person_dto); 
		
		return new ResponseEntity<HashMap<String, Object>>(response,HttpStatus.OK); 
	}
	
	@GetMapping("/{dni}") //obtiene una persona por su dni
	public ResponseEntity<HashMap<String, Object>> getPersonByDni(@PathVariable String dni) { 
		
		HashMap<String,Object> response =new HashMap<>(); 
		
		Person person=personService.findByDni(dni); 
		
		PersonDTO person_dto=PersonWrapper.entitytopersondto(person); 
		person_dto.setKey_code("Protegido"); 
		
		response.put("Persona: ", person_dto); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@GetMapping("/person/{last_name}") //obtiene personas por apellido
	public ResponseEntity<HashMap<String,Object>> getPersonByLastName(@PathVariable String last_name) { 
		
		HashMap<String,Object> response=new HashMap<>(); 
		
		List<PersonDTO> persondto= new ArrayList(); 
		
		personService.findByLastname(last_name).forEach(persona -> { 
			persondto.add(PersonWrapper.entitytopersondto(persona)); 
		}); 
		
		for (int i=0; i<persondto.size(); i++) 
		{ 
			persondto.get(i).setKey_code("Protegido"); 
		}
		response.put("Persona:", persondto); 
		
		return new ResponseEntity<HashMap<String, Object>> (response,HttpStatus.OK); //retorna una persona
	}
	
	@PutMapping("/update/{dni}") //actualiza una persona por su dni 
	public ResponseEntity<HashMap<String,Object>> update(@PathVariable String dni, @RequestBody PersonDTO persondto ){
		
		HashMap<String,Object> response=new HashMap<>(); 
		
		Person person=personService.findByDni(dni); 
		person=personService.updatePerson(person, persondto); 
		PersonDTO new_person=PersonWrapper.entitytopersondto(person); 
		new_person.setKey_code("Protegido"); 
		response.put("Persona:", new_person); 
		return new ResponseEntity<HashMap<String,Object>>(response, HttpStatus.OK); 
	}
	
	@DeleteMapping("/delete/{dni}")
	public ResponseEntity<HashMap<String,Object>> deletePerson(@PathVariable String dni, @RequestBody PersonDTO persondto) { 
		
		Person person=personService.findByDni(dni);
		PersonDTO person_dto; 
		HashMap<String,Object> response=new HashMap<>(); 
		
		person=personService.Compare_key(person, persondto);
		
		person=personService.deletePerson(person); 
		person_dto=PersonWrapper.entitytopersondto(person); 
		person_dto.setKey_code("Protegido"); 
		response.put("Persona:", person_dto); 
		return new ResponseEntity<HashMap<String, Object>>(response,HttpStatus.OK); 
	}

}
