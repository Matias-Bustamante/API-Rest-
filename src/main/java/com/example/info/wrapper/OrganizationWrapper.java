package com.example.info.wrapper;
import com.example.info.utils.generar_key;

import java.util.GregorianCalendar;
import java.util.UUID;

import com.example.info.dto.OrganizationDTO;
import com.example.info.model.Organization;

public class OrganizationWrapper {

	public static Organization organizationdtoentity(OrganizationDTO organization_dto) 
	{ 
		
		String key; 
		key=UUID.randomUUID().toString().substring(0,generar_key.generar_key()); 
		Organization organization=new Organization(); 
		organization.setName(organization_dto.getName());
		organization.setAddress(organization_dto.getAddress());
		organization.setPhone(organization_dto.getPhone());
		organization.setEmail(organization_dto.getEmail());
		organization.setDischarge_date(new GregorianCalendar());
		organization.setDelete(false); // false=NO Borrado Logico True=Borrado LOgico
		organization.setKey_code(key);
		organization.setCuit(organization_dto.getCuit());
		
		return organization; 
	}
	
	public static OrganizationDTO entityToOrganizationDTO(Organization organization) { 
		OrganizationDTO organization_dto =new OrganizationDTO(); 
		organization_dto.setName(organization.getName());
		organization_dto.setAddress(organization.getAddress());
		organization_dto.setEmail(organization.getEmail());
		organization_dto.setPhone(organization.getPhone());
		
		return organization_dto; 
	}
	

	
}
