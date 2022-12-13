package com.example.info.dto;

import java.util.Calendar;

import com.example.info.model.Event;
import com.example.info.model.Person;

public class TurnDTO {
	
	private Calendar date_turn; 
	
	private Event event; 
	
	private Person person; 
	
	private String name_organization; 
	
	public TurnDTO() 
	{ 
		
	}

	public TurnDTO(Calendar date_turn, Event event, Person person, String name_organization) {
		super();
		this.date_turn = date_turn;
		this.event = event;
		this.person = person;
		this.name_organization = name_organization; 
	}

	public Calendar getDate_turn() {
		return date_turn;
	}

	public void setDate_turn(Calendar date_turn) {
		this.date_turn = date_turn;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName_organization() {
		return name_organization;
	}

	public void setName_organization(String name_organization) {
		this.name_organization = name_organization;
	}
	
	

}
