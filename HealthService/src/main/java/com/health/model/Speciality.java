package com.health.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "speciality")
public class Speciality {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String title;

	@OneToMany(targetEntity = Doctor.class, cascade = CascadeType.MERGE, mappedBy = "speciality")
	private Set<Doctor> doctors = new HashSet<Doctor>();

//Constructor

	public Speciality() {
		// TODO Auto-generated constructor stu {
		super();
		// TODO Auto-generated constructor stub
	}

	public Speciality(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

//Getters and Setters 

	public String getId() {

		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", title=" + title + "]";
	}

}
