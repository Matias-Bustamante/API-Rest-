package com.example.info.dto;



public class OrganizationDTO {

	private String name; 
	
	private String address; 
	
	private String phone; 
	
	private String email; 
	
	private String key_code; 
	
	private String cuit; 
	
	public OrganizationDTO() { 
		
	}

	public OrganizationDTO(String name, String address, String phone, String email, String key_code, String cuit) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.key_code=key_code; 
		this.cuit=cuit; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	
	
	
}
