package com.example.info.exception;

public class Not_Found extends RuntimeException{

	public Not_Found() 
	{ 
		
	}
	
	public Not_Found(String messages) 
	{ 
		super(messages); 
	}
}
