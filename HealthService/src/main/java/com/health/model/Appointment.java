package com.health.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_sequence")
    @SequenceGenerator(name = "entity_sequence", sequenceName = "entity_sequence", allocationSize = 1)
    private Long id;
	private String patientid;
	private String patientname;
	private String email;
	private String doctorid;
	private String doctorname;
	private String specialization;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;
	private int age;
	private String gender;
	private String slot;
	private String status;
	private String problem;
	private String paymentstatus;

	
	
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(Long id, String patientid, String patientname, String email, String doctorid,
			String doctorname, String specialization, Date startdate, int age, String gender, String slot,
			String status, String problem,String paymentstatus) {
		super();
		this.id = id;
		this.patientid = patientid;
		this.patientname = patientname;
		this.email = email;
		this.doctorid = doctorid;
		this.doctorname = doctorname;
		this.specialization = specialization;
		this.startdate = startdate;
		this.age = age;
		this.gender = gender;
		this.slot = slot;
		this.status = status;
		this.problem = problem;
		this.paymentstatus=paymentstatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getAppointmentstatus() {
		return status;
	}

	public void setAppointmentstatus(String status) {
		this.status = status;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	
	

}