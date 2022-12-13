package com.example.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.info.dao.IOrganizationDAO;
import com.example.info.dto.OrganizationDTO;
import com.example.info.exception.BAD_Request;
import com.example.info.exception.KeyIncorrecta;
import com.example.info.exception.Not_Found;
import com.example.info.exception.SearchActivateOrg;
import com.example.info.model.Organization;

@Service
public class OrganizationServiceImpl implements IOrganizationService{

	@Autowired
	private IOrganizationDAO organization_dao; 
	
	@Override
	public Organization save(Organization organization)  {
		// TODO Auto-generated method stub
		 
		if(organization.getName().isEmpty() || organization.getAddress().isEmpty() || organization.getCuit().isEmpty() || organization.getPhone().isEmpty() || organization.getEmail().isEmpty() || organization.getAddress().isEmpty()) 
		{ 
			 throw new BAD_Request("Los campos no pueden estar vacios"); 
		}
		return organization_dao.save(organization);
			
	}
	
	@Override
	public Organization getOrganizationByName(String name) {
		// TODO Auto-generated method stub
		Organization organization; 
		
		if (organization_dao.existsByName(name)) 
		{ 
			organization = organization_dao.findByName(name);
			if (organization.isDelete()==false) 
			{ 
				return organization; 
			}
		}
		
		throw new Not_Found("El nombre: "+name + " No existe intente nuevamente"); 
			
	}
	
	@Override
	public Organization updateOrganizationByName(Organization organization, OrganizationDTO organization_dto) {
		
			boolean cond =Compare_key(organization.getKey_code(), organization_dto.getKey_code()); 
			
			if (cond)
			{ 
			organization.setName(organization_dto.getName());
			organization.setAddress(organization.getAddress());
			organization.setPhone(organization_dto.getPhone());
			organization.setEmail(organization.getEmail());
			return organization_dao.save(organization); 
			}
			throw new KeyIncorrecta("Clave Incorrecta"); 
	}
	
	@Override
	public Organization searchActivate(Organization organization) {
		// TODO Auto-generated method stub
		if (organization.isDelete()==false) 
		{ 
			return organization; 
		}
		throw new SearchActivateOrg("No existe ninguna organizacion"); 
	}
	
	@Override
	public Organization deleteOrganization(Organization organization) {
		// TODO Auto-generated method stub
		organization.setDelete(true);
		return organization; 
	}
	
	
	public boolean Compare_key(String clave, String clave2) {
		// TODO Auto-generated method stub
		
		if (clave.equals(clave2))
		{ 
			return true; 
		}
		return false; 
	}
	
	

}
