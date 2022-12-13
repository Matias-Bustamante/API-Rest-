package com.example.info.wrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.info.dto.EventDTO;
import com.example.info.model.Event;
import com.example.info.model.Organization;
import com.example.info.model.Status;

public class EventWrapper {
	
	public static Event EventDTOentity(EventDTO event_dto) 
	{ 
		Event event = new Event(); 
		event.setName(event_dto.getName());
		event.setLocation(event_dto.getLocation());
		event.setDate_event(event_dto.getDate_event());
		event.setPeriodicity(event_dto.getPeriodicity()); //Periodicity UNICO=0 RECURRENTE=1
		event.setDischarge_date(new GregorianCalendar());
		event.setStatus(settearStatus(event_dto.getDate_event())); 
		event.setOrganizations(event_dto.getOrganization());
		
		return event; 
	}
	
	public static EventDTO entityTOEventDTO(Event event) 
	{ 
		EventDTO event_dto=new EventDTO(); 
		event_dto.setName(event.getName());
		event_dto.setDate_event(event.getDate_event());
		event_dto.setLocation(event.getLocation());
		event_dto.setPeriodicity(event.getPeriodicity());
			
		return event_dto; 
	}
	
	public static Status settearStatus (Calendar date_event) 
	{ 
		Calendar cal=Calendar.getInstance(); 
		cal.clear();
		cal.set(Calendar.YEAR,date_event.getTime().getYear()+1900); 
		cal.set(Calendar.MONTH,date_event.getTime().getMonth());  
		cal.set(Calendar.DATE, date_event.getTime().getDate()+1);
		int fecha=cal.getTime().compareTo(new Date()); 
		
		if (fecha>=0) 
		{ 
			return Status.ACTIVO; 
		}
		return Status.INACTIVO; 
	}
}
