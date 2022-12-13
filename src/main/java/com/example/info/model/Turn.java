package com.example.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter; 

@Data()
@Setter()
@Getter()

@Entity()
@Table(name = "turns")
public class Turn implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name ="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar date; 
	
	@Column(name ="key_code", nullable=false, unique=true)
	private String key_code; 
	
	@Column(name ="status", nullable=false)
	private Status status; 
	
	
	@ManyToOne
	@JoinColumn( name = "event_id", nullable=false)
	@JsonIgnore
	private Event event; 
	
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name ="person_id", referencedColumnName="id") 
	@JsonIgnore
	private Person person; 
	
	public Turn() { 
		
	}

	public Turn(Long id, Calendar date, String key_code, Status status, Event event, Person person) {
		super();
		this.id = id;
		this.date = date;
		this.key_code = key_code;
		this.status = status;
		this.event = event;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
	
	
	
}
