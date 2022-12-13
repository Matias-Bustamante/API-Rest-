package com.example.info.exception.PersonException;

public class Not_FoundDni extends RuntimeException{ 
	
	public Not_FoundDni() { 
		
	}
	
	public Not_FoundDni(String messages) 
	{ 
		super(messages); 
	}

}
