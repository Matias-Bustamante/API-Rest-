package com.example.info.service;

import java.util.List;

import com.example.info.dto.EventDTO;
import com.example.info.model.Event;

public interface IEventService {
	
	public Event SaveEvent(Event event); 
	
	public List<Event> getAllEvent(); 
	
	public List<Event> getEventByName(String name); 
	
	public Event updateEvent(EventDTO event_dto, Long id); 
	
	public Event deleteEvent(Event event); 
	
	public Event getEventById(Long Id); 
	
	public List<Event> EventActivate(); 
	
	public Event findEvent(String name, String org); 
	
	public Event update(Event event); 
	
	public Event delete(Event event); 

}
