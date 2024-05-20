package com.health.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String patientid;
	private String doctorid; 
	private Long appointmentid;
	private double amount;
	

	public PaymentDetails() {
	}

	public PaymentDetails(String patientid, String doctorid,Long appointmentid, double amount) {
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.appointmentid=appointmentid;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	
	
	
	
	public Long getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(Long appointmentid) {
		this.appointmentid = appointmentid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
