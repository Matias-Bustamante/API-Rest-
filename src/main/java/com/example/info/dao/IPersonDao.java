package com.example.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.info.model.Person;

@Repository
public interface IPersonDao extends JpaRepository<Person, Long>{
	
	public Person findByDni(String dni); 
	
	public List<Person> findByLastname(String last_name); 
	
	public boolean existsByDni(String dni); 
	
	public boolean existsByLastname(String last_name); 
	
	

}
