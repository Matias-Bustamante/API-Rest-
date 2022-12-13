package com.example.info.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.info.dao.ITurnDAO;
import com.example.info.dto.TurnDTO;
import com.example.info.exception.BAD_Request;
import com.example.info.exception.Not_Found;
import com.example.info.model.Event;
import com.example.info.model.Person;
import com.example.info.model.Status;
import com.example.info.model.Turn;
import com.example.info.service.IEventService;
import com.example.info.service.IPersonService;
import com.example.info.service.ITurnService;
import com.example.info.wrapper.TurnWrapper;



@RestController
@RequestMapping("/api/v1/turns")
public class TurnController {
	
	@Autowired
	private ITurnService turn_service; 
	
	@Autowired
	private IEventService event_service; 
	
	@Autowired 
	private IPersonService person_service; 
	
	@PostMapping("saveTurn/recurrente")
	public ResponseEntity<HashMap<String,Object>> SaveTurn(@RequestBody TurnDTO turn_dto) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		
		Turn turn=TurnWrapper.TurnDTOentity(turn_dto);  
		Person persona=person_service.findByDni(turn_dto.getPerson().getDni()); 
		turn.setPerson(persona); 
		turn=turn_service.SaveTurn(turn);  
		
		response.put("Turno: ", turn); 
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@PostMapping("/event/unico")
	public ResponseEntity<HashMap<String,Object>> TurnOFEvent(@RequestBody TurnDTO turn_dto) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		if (turn_dto.getEvent().getDate_event()!=null) 
		{ 
			Turn turn =TurnWrapper.TurnDTOUniqueentity(turn_dto); 
			Person persona=person_service.findByDni(turn_dto.getPerson().getDni()); 
			turn.setPerson(persona); 
			turn_service.SaveTurnUnique(turn); 
			response.put("Turn", turn); 
			
			return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK);
		}
		throw new Not_Found("Error debes de introducir la fecha del evento"); 
		 
	}
	
	
	@GetMapping("/getEvent/{name}")
	public ResponseEntity<HashMap<String,Object>> getTurnByOrganizationByEvents(@PathVariable String name) 
	{ 
		List<Turn> list_turn=turn_service.getTurnByName(name); 
		HashMap<String,Object> response=new HashMap<>(); 
		
		for (int i=0; i<list_turn.size(); i++) 
		{ 
			response.put("Date Turn", list_turn.get(i).getDate()); 
			response.put("Name Event:", list_turn.get(i).getEvent().getName()); 
			response.put("Name Organization: ", list_turn.get(i).getEvent().getOrganizations().getName()); 
			response.put("Name Person: ", list_turn.get(i).getPerson().getFirstname()); 
		}
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<HashMap<String,Object>> updateTurns(@RequestBody TurnDTO turn_dto, @PathVariable Long id) 
	{ 
		List<Turn> turnos=turn_service.getAllTurn(); 
		Turn Turnos= new Turn(); 
		HashMap<String,Object> response = new HashMap<>();  
		
		for (int i=0; i<turnos.size(); i++) 
		{ 
			if (turnos.get(i).getId()==id) 
			{ 
				Turnos=turnos.get(i); 
			}
		}
		Turnos.setDate(turn_dto.getDate_turn()); 
		
		 Turnos=turn_service.updateTurn(Turnos); 
		 
		
		response.put("Messages:", "Turno modificado con exito"); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<HashMap<String,Object>> deleteTurn(@PathVariable Long id) 
	{ 
		List<Turn> turn=turn_service.getAllTurn(); 
		HashMap<String,Object> response=new HashMap<>(); 
		Turn turnos=null; 
		
		for ( int i=0; i<turn.size(); i++) 
		{ 
			if (turn.get(i).getId()==id) 
			{ 
				turnos=turn.get(i); 
			}
		}
	
		if (turnos!=null) 
		{ 
			turnos.setStatus(Status.INACTIVO); 
			turnos=turn_service.deleteTurnById(turnos); 
			response.put("Messages: ", "El turno fue modificado con exito"); 
			response.put("Turno", turnos); 
			return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
			
		}
		throw new BAD_Request("Error el Id ingresado no existe. Intente nuevamente"); 
		
		
		
	}
	

}
