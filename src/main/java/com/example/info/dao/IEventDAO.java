package com.example.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.info.model.Event;

@Repository
public interface IEventDAO extends JpaRepository<Event,Long>{
	
	public List<Event> findByName(String name); 
	
	public boolean existsByName(String name); 
	
	
	
	

}
