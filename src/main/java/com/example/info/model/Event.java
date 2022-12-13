package com.example.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.Getter;
import lombok.Setter; 

@Data()
@Setter()
@Getter()
@Entity
@Table(name = "events")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id; 
	
	@Column(name ="name", nullable=false)
	private String name; 
	
	@Column(name ="location", nullable=false)
	private String location; 
	
	@Column(name="status", nullable=false)
	private Status status; 
	
	@Column(name ="discharge_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar discharge_date; 
	
	@Column(name ="periodicity", nullable=false)
	private Periodicity periodicity; 
	
	@Column(name ="date_event", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar date_event; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="organizations_id")
	@JsonBackReference
	private Organization organizations; 
	
	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(
			name ="event_person", 
			joinColumns = @JoinColumn(name ="event_id", nullable=false),
			inverseJoinColumns= @JoinColumn(name ="person_id", nullable=false)
			)
	@JsonIgnore
	private List<Person> person =new ArrayList<Person>(); 
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy= "event")
	private List<Turn> turn = new ArrayList<Turn>(); 
	
	public Event () 
	{ 
		
	}

	public Event(long id, String name, String location, Status status, Calendar discharge_date, Periodicity periodicity,
			Calendar date_event, Organization organizations, List<Person> person, List<Turn> turn) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.status = status;
		this.discharge_date = discharge_date;
		this.periodicity = periodicity;
		this.date_event = date_event;
		this.organizations = organizations;
		this.person = person;
		this.turn = turn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getDischarge_date() {
		return discharge_date;
	}

	public void setDischarge_date(Calendar discharge_date) {
		this.discharge_date = discharge_date;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public Calendar getDate_event() {
		return date_event;
	}

	public void setDate_event(Calendar date_event) {
		this.date_event = date_event;
	}

	public Organization getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Organization organizations) {
		this.organizations = organizations;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public List<Turn> getTurn() {
		return turn;
	}

	public void setTurn(List<Turn> turn) {
		this.turn = turn;
	}
	
	
	
}
