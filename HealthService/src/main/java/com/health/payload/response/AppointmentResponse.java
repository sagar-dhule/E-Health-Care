package com.health.payload.response;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AppointmentResponse {

	
	private Long id;
	private String patientid;
	private String patientname;
	private String doctorid;
	private String doctorname;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String slot;
    private String status;
    private String payment;
    private double amount;
    private String reportid;
    
    
	public AppointmentResponse() {
		super();
		
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


	public String getDoctorname() {
		return doctorname;
	}


	public String getPatientname() {
		return patientname;
	}


	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}


	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getSlot() {
		return slot;
	}


	public void setSlot(String slot) {
		this.slot = slot;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getReportid() {
		return reportid;
	}


	public void setReportid(String reportid) {
		this.reportid = reportid;
	}


	public String getDoctorid() {
		return doctorid;
	}


	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
    
    
}
