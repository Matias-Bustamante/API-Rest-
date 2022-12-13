package com.example.info.exception;

public class BAD_Request  extends RuntimeException{ 
	
	public BAD_Request() 
	{ 
		
	}
	
	public BAD_Request(String messages) 
	{ 
		super(messages); 
	}
	

}
