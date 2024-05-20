package com.health.payload.request;

public class PrescriptionRequest {

	private String prescription;
	private String patientid;
	private Long appointmentid;
	
	
	public PrescriptionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrescription() {
		return prescription;
	}


	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}


	public String getPatientid() {
		return patientid;
	}


	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}


	public Long getAppointmentid() {
		return appointmentid;
	}


	public void setAppointmentid(Long appointmentid) {
		this.appointmentid = appointmentid;
	}
	
	
	
}
