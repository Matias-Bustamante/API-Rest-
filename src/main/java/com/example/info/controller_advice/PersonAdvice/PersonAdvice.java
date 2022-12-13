package com.example.info.controller_advice.PersonAdvice;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.info.controller_advice.Controller_Advice;
import com.example.info.exception.PersonException.FieldsEmpty;
import com.example.info.exception.PersonException.Not_FoundDni;
import com.example.info.exception.PersonException.PersonEmpty;

@ControllerAdvice(annotations=RestController.class)
public class PersonAdvice  extends Controller_Advice{
	

	@ExceptionHandler(FieldsEmpty.class)
	public ResponseEntity<HashMap<String,Object>> FieldsEmptyExceptio(Exception ex) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		 
		response.put("Messages:", ex.getMessage());  
		return new ResponseEntity<HashMap<String,Object>> (response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PersonEmpty.class)
	public ResponseEntity<HashMap<String,Object>> PersonEmptyException(Exception ex) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		 
		response.put("Messages:", ex.getMessage());  
		return new ResponseEntity<HashMap<String,Object>> (response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Not_FoundDni.class)
	public ResponseEntity<HashMap<String,Object>> Not_FopundDniException(Exception ex) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		 
		response.put("Messages:", ex.getMessage());  
		return new ResponseEntity<HashMap<String,Object>> (response,HttpStatus.BAD_REQUEST);
	}
	
	
	

}
