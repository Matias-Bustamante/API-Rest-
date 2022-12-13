package com.example.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.info.model.Turn;

@Repository
public interface ITurnDAO extends JpaRepository<Turn,Long>{
	
	public List<Turn> findAllById(Long Id); 
	
	public Turn save(Turn turn); 

}
