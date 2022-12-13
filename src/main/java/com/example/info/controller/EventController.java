package com.example.info.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

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

import com.example.info.dto.EventDTO;
import com.example.info.model.Event;
import com.example.info.model.Organization;
import com.example.info.service.IEventService;
import com.example.info.service.IOrganizationService;
import com.example.info.wrapper.EventWrapper;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
	@Autowired
	private IEventService event_service; 
	
	@Autowired
	private IOrganizationService organization_service; 
	
	@PostMapping() //Agrega un Evento 
	public ResponseEntity<HashMap<String,Object>> createEvent(@RequestBody EventDTO event_dto) { 
		
		HashMap<String,Object> response=new HashMap<>(); 
		
		Event event = EventWrapper.EventDTOentity(event_dto); 
		
		event=event_service.SaveEvent(event); 
		response.put("Evento", event_dto); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
		
		
	}
	
	@GetMapping("/{name}") //Obtiene evento por name 
	public ResponseEntity<HashMap<String,Object>> getEventName(@PathVariable String name) 
	{ 
		List<Event> event = event_service.getEventByName(name); 
		HashMap<String,Object> response=new HashMap<>(); 
		List<EventDTO> event_dto=new ArrayList(); 
		event.forEach(new_event -> { 
			event_dto.add(EventWrapper.entityTOEventDTO(new_event)); 
 
		}); 
		response.put("Evento", event_dto); 
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@PutMapping("update/{name}/{org}") // Actualiza un evento
	public ResponseEntity<HashMap<String,Object>> updateEventByName(@RequestBody EventDTO event_dto, @PathVariable String name, @PathVariable String org) { 
		HashMap<String,Object> response =new HashMap<>(); 
		Event new_event=event_service.findEvent(name, org); 
		
		new_event.setDate_event(event_dto.getDate_event()); 
		new_event.setLocation(event_dto.getLocation()); 
		new_event.setName(event_dto.getName()); 
		new_event.setPeriodicity(event_dto.getPeriodicity());
		new_event.setStatus(EventWrapper.settearStatus(event_dto.getDate_event()));  
		System.out.println(new_event.getStatus()); 
		new_event=event_service.update(new_event); 
		Event event=EventWrapper.EventDTOentity(event_dto); 
		
		response.put("Evento: ", event);
		response.put("Messages:", "Evento modificado con exito"); 
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
	}
	
	@DeleteMapping("/{name_event}/{name_org}")
	public ResponseEntity<HashMap<String,Object>> deleteEvent(@PathVariable String name_event, @PathVariable String name_org) 
	{ 
		HashMap<String,Object> response=new HashMap<>(); 
		Event new_event=event_service.findEvent(name_event, name_org); 
		new_event=event_service.delete(new_event); 
		response.put("Event: ", new_event); 
		response.put("Messages: ", "Evento eliminado con exito"); 
		
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK); 
		
	}

}
