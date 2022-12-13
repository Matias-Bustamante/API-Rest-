package com.example.info.controller;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.info.dao.IOrganizationDAO;
import com.example.info.dto.OrganizationDTO;
import com.example.info.exception.KeyIncorrecta;
import com.example.info.model.Organization;
import com.example.info.service.IOrganizationService;
import com.example.info.service.OrganizationServiceImpl;
import com.example.info.wrapper.OrganizationWrapper;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
	
	@Autowired
	private IOrganizationService organization_service; 
	
	
	@PostMapping //crea una organizacion 
	public ResponseEntity<HashMap<String,Object>> createOrganization(@RequestBody  OrganizationDTO organization_dto) { 
		
		Organization organization = OrganizationWrapper.organizationdtoentity(organization_dto); 
		HashMap<String,Object> response=new HashMap<>(); 
		
		organization=organization_service.save(organization); 
		
		response.put("Organization", organization_dto); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
		
	}
	
	@GetMapping("/{name}") //obtiene una organizacion por nombre
	public ResponseEntity<HashMap<String,Object>> getOrganization(@PathVariable String name) 
	{ 
		
		Organization organization=organization_service.getOrganizationByName(name);
		OrganizationDTO organization_dto=OrganizationWrapper.entityToOrganizationDTO(organization); 
		HashMap<String,Object> response=new HashMap<>(); 
		response.put("Organization", organization_dto); 
		return new ResponseEntity<HashMap<String, Object>>(response,HttpStatus.OK); 
	}
	
	@PutMapping("/update/{name}") //actualiza una organizacion por su nombre
	public ResponseEntity<HashMap<String,Object>> updateOrganization(@PathVariable String name, @RequestBody OrganizationDTO organization_dto) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		Organization organization=organization_service.getOrganizationByName(name); 
		organization=organization_service.searchActivate(organization); 
		organization=organization_service.updateOrganizationByName(organization, organization_dto); 
		response.put("Organization: ", organization); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@DeleteMapping("/{name}") //elimina una organizacion por su nombre
	public ResponseEntity<HashMap<String,Object>> deleteOrganization(@PathVariable String name, @RequestBody OrganizationDTO dto) 
	{ 
		Organization organization = organization_service.getOrganizationByName(name); 
		HashMap<String,Object> response = new HashMap<>();
		
		organization=organization_service.searchActivate(organization); 
		
		if (organization.getKey_code().equals(dto.getKey_code())) 
		{ 
			organization=organization_service.deleteOrganization(organization); 
			organization_service.save(organization); 
			response.put("Message", "Eliminacion con exito"); 
			return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
		}
		throw new KeyIncorrecta("Clave Incorrecta"); 
			
	}

}
