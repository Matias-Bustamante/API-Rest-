package com.example.info.dto;

public class PersonDTO {

	private String first_name; 
	
	private String last_name; 
	
	private String dni; 
	
	private String key_code; 
	
	public PersonDTO() { 
		
	}

	public PersonDTO(String first_name, String last_name, String dni, String key_code) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.dni = dni;
		this.key_code=key_code; 
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}
	
	
	
	
}
