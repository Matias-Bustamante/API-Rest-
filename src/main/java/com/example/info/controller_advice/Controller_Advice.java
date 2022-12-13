package com.example.info.controller_advice;

import java.util.HashMap;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.info.exception.BAD_Request;
import com.example.info.exception.KeyIncorrecta;
import com.example.info.exception.Not_Found;
import com.example.info.exception.SearchActivateOrg;
import com.example.info.exception.PersonException.FieldsEmpty;


@ControllerAdvice(annotations=RestController.class)
public class Controller_Advice {
	
	@ExceptionHandler(BAD_Request.class)
	public ResponseEntity<HashMap<String,Object>> BadException(Exception e) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		response.put("Messages:", e.getMessage()); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) 
	public ResponseEntity<HashMap<String,Object>> DataViolation(Exception ex) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		response.put("Messages:", "Error no se puede realizar la operacion violacion de integridad de los datos"+ ex.getMessage()); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	
	@ExceptionHandler({Not_Found.class, SearchActivateOrg.class}) 
	public ResponseEntity<HashMap<String,Object>> NotFoundExceptio(Exception ex) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		response.put("Messages: ", ex.getMessage()); 
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(KeyIncorrecta.class)
	public ResponseEntity<HashMap<String,Object>> keyException(Exception ex) 
	{ 
		HashMap<String, Object> response=new HashMap<>(); 
		
		response.put("Messages:", ex.getMessage()); 
		
		return new ResponseEntity<HashMap<String, Object>>(response,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ResponseEntity<HashMap<String,Object>> DataAccessException() 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		 
		response.put("Messages:", "Servidor desconectado. Intentalo más tarde");  
		return new ResponseEntity<HashMap<String,Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ExceptionHandler(NullPointerException.class) 
	public ResponseEntity<HashMap<String,Object>> PointerException(Exception ex) 
	{
		HashMap<String,Object> response=new HashMap<>(); 
		response.put("Messages: ",ex.getMessage()); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	

}
