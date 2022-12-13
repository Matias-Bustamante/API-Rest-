package com.example.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.info.dao.IEventDAO;
import com.example.info.dao.IOrganizationDAO;
import com.example.info.dto.EventDTO;
import com.example.info.exception.Not_Found;
import com.example.info.model.Event;
import com.example.info.model.Organization;
import com.example.info.model.Status;

@Service
public class EventServiceImpl implements IEventService{
	
	@Autowired
	private IEventDAO eventdao; 
	
	@Autowired
	private IOrganizationDAO organization_dao; 

	@Override
	public Event SaveEvent(Event event) {
		// TODO Auto-generated method stub
		String name=event.getOrganizations().getName(); 
		
		if (organization_dao.existsByName(name)) 
		{ 
			if (Verificar_Condicion(event)) 
			{ 
				Organization organization=organization_dao.findByName(name); 
				event.setOrganizations(organization);
				return eventdao.save(event); 
			}
			throw new Not_Found("La organizacion ya tiene un evento con el mismo nombre activo"); 
			
		}
		throw new Not_Found("No existe la Organizacion"); 
		
	}

	@Override
	public List<Event> getAllEvent() {
		// TODO Auto-generated method stub
		return eventdao.findAll(); 
		}
	
	public List<Event> getAllActivate() { 
		List<Event> event= new ArrayList(); 
		eventdao.findAll().forEach(evento -> { 
			if (evento.getStatus().ACTIVO.toString().equals("ACTIVO")) 
			{ 
				event.add(evento); 
			}
		});
		
		return event; 
	}

	@Override
	public List<Event> getEventByName(String name) {
		// TODO Auto-generated method stub
		if (eventdao.existsByName(name)) 
		{ 
			return eventdao.findByName(name); 
		}
		throw new Not_Found("No existe el evento ingresado"); 
		
	}

	@Override
	public Event updateEvent(EventDTO event_dto, Long id) {
		// TODO Auto-generated method stub
		Event nuevo_evento=eventdao.getById(id);  
		
		if (nuevo_evento != null) 
		{ 
			nuevo_evento.setLocation(event_dto.getLocation());
			nuevo_evento.setDate_event(event_dto.getDate_event());
			nuevo_evento.setPeriodicity(event_dto.getPeriodicity());
		
			return eventdao.save(nuevo_evento); 
		}
				
		return null;  
	}
	
	public List<Event> EventActivate() 
	{ 
		List<Event> event=new ArrayList(); 
		
		eventdao.findAll().forEach(evento -> {
			if (evento.getStatus().toString().equals("ACTIVO")) 
			{ 
				event.add(evento); 
			}
		});
		
		return event; 
	}

	@Override
	public Event deleteEvent(Event event) {
		// TODO Auto-generated method stub
		event.setStatus(Status.INACTIVO);
		return eventdao.save(event); 
	}

	@Override
	public Event getEventById(Long Id) {
		// TODO Auto-generated method stub
		return eventdao.getById(Id); 
	}

	@Override
	public Event findEvent(String name, String org) {
		// TODO Auto-generated method stub
		List<Event> eventos=getEventByName(name); 
		Event new_event=null; 
		for (int i=0; i<eventos.size(); i++) 
		{ 
			if (eventos.get(i).getOrganizations().getName().equals(org)) 
			{ 
				new_event=eventos.get(i); 
			}
		}
		
		if (new_event == null) 
		{ 
			throw new Not_Found("Ingreso incorrectamente la organizacion"); 
		}
		return new_event; 
	}

	
	public boolean Verificar_Condicion(Event event) 
	{ 
		List<Event> eventos=EventActivate(); 
		int cont=0; 
		for (int i=0; i<eventos.size(); i++) 
		{ 
			if (eventos.get(i).getName().equals(event.getName()) && eventos.get(i).getOrganizations().getName().equals(event.getOrganizations().getName())) 
			{ 
				cont=1; 
			}
		}
		
		if (cont==1) 
		{ 
			return false; 
		}
		return true; 
	}

	@Override
	public Event update(Event event) {
		// TODO Auto-generated method stub
		
		return eventdao.save(event); 
	}
	
	@Override
	public Event delete(Event event) 
	{ 
		event.setStatus(Status.INACTIVO);
		return eventdao.save(event); 
	}
	
	

}
