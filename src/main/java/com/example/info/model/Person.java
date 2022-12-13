package com.example.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter; 


@Data()
@Setter()
@Getter()

@Entity()
@Table(name ="persons")

public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "first_name", nullable=false)
	private String firstname; 
	
	@Column(name ="last_name", nullable=false)
	private String lastname; 
	
	@Column(name ="dni", nullable=false, unique=true, length=8)
	@Pattern(regexp = "[6][0-9]{8}")
	private String dni;
	
	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar date; 
	
	@Column(name= "status", nullable=false)
	private Status status; 
	
	@Column(name="key_code", nullable=false, unique=true)
	private String key_code; 
	
	@ManyToMany(mappedBy= "person")
	private List<Event> event = new ArrayList<Event>(); 
	
	@OneToOne(mappedBy= "person", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private Turn turn; 
	
	public Person() { 
		
	}

	public Person(long id, String firstname, String lastname, String dni, Calendar date, Status status, String key_code,
			List<Event> event, Turn turn) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dni = dni;
		this.date = date;
		this.status = status;
		this.key_code = key_code;
		this.event = event;
		this.turn = turn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
