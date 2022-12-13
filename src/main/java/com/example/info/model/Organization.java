package com.example.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter; 

@Data()
@Setter()
@Getter()
@Entity()
@Table(name="organizations")
public class Organization implements Serializable { 
	
	 private static final long serialVersionUID = 1L; 
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long Id; 

	 @Column(name = "name", nullable=false)
	 private String name; 
	 
	 @Pattern(regexp = "[6][0-9]{11}")
	 @Column(name ="cuit", nullable=false, unique=true, length=11)
	 private String cuit; 
	 
	 @Column(name ="address", nullable=false)
	 private String address; 
	 
	 @Pattern(regexp = "[6][0-9]{8}")
	 @Column(name ="phone", nullable=false, length=8, unique=true) 
	 private String phone; 
	 
	 @Email
	 @Column(name = "email", nullable=false, unique=true)
	 private String email; 
	 
	 @Column(name = "discharge_date")
	 @Temporal(TemporalType.DATE)
	 private Calendar discharge_date; 
	 
	 
	 @Column(name = "key_code")
	 private String key_code;  
	 
	 @Column(name = "delete")
	 private boolean delete; //especifica si la organizacion esta borrado logicamente 0 indica no esta borrado y 1 si esta borrado logicamente
	 
	 @OneToMany(mappedBy="organizations", cascade=CascadeType.ALL)
	 @JsonManagedReference
	 private List<Event> event= new ArrayList<Event>(); 

	 public Organization () { 
		 
	 }

	public Organization(long id, String name, String address, String phone, String email, Calendar discharge_date,
			String key_code, boolean delete, List<Event> event, String cuit) {
		super();
		Id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.discharge_date = discharge_date;
		this.key_code = key_code;
		this.delete = delete;
		this.event = event;
		this.cuit=cuit; 
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDischarge_date() {
		return discharge_date;
	}

	public void setDischarge_date(Calendar discharge_date) {
		this.discharge_date = discharge_date;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	 
	 
	 
	 
	 
}
