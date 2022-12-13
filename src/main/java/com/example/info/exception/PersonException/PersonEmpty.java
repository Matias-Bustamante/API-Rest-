package com.example.info.exception.PersonException;

public class PersonEmpty extends RuntimeException {
	
	public PersonEmpty() 
	{ 
		
	}
	
	public PersonEmpty(String messages) 
	{ 
		super(messages); 
	}

}
