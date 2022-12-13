package com.example.info.dto;

import java.util.Calendar;

import com.example.info.model.Organization;
import com.example.info.model.Periodicity;

public class EventDTO {
	
	private String name; 
	
	private String location; 
	
	private Calendar date_event; 
	
	private Periodicity periodicity; 
	
	private Organization organization; 
	
	public EventDTO() { 
		
	}

	public EventDTO(String name, String location, Calendar date_event, Periodicity periodicity, Organization organization) {
		super();
		this.name = name;
		this.location = location;
		this.date_event = date_event;
		this.periodicity = periodicity;
		this.organization=organization; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Calendar getDate_event() {
		return date_event;
	}

	public void setDate_event(Calendar date_event) {
		this.date_event = date_event;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	
	
	
	

}
