package com.example.info.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.info.model.Organization;

@Repository
public interface IOrganizationDAO extends JpaRepository<Organization,Long>{
	
	public Organization findByName(String name); 
	
	public boolean existsByName(String name); 
	
}
