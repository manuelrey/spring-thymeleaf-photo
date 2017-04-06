package com.manuelrey.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String first_name;
	@NotEmpty
	private String last_name;
	@NotEmpty
	private String email;
	private String photo;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String first_name, String last_name, String email, String photo) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
