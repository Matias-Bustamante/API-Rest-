package com.example.info.service;

import com.example.info.dto.OrganizationDTO;
import com.example.info.model.Organization;


public interface IOrganizationService {
	
	public Organization save(Organization organization); 
	
	public Organization getOrganizationByName(String name); 
	
	public Organization updateOrganizationByName(Organization organization, OrganizationDTO organization_dto); 
	
	public Organization searchActivate(Organization organization); 

	public Organization deleteOrganization(Organization organization); 
	
	
}
