package com.health.payload.response;

import java.util.List;

public class Patient {
	
	private String patientid;
	private String patientname;
	private String email;
	private String gender;
	private String age;
	private String mobile;
	private String address;
	private List<PatientReport> reportlist;
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Patient(String patientid, String patientname, String email, String gender, String age, String mobile,
			String address, List<PatientReport> reportlist) {
		super();
		this.patientid = patientid;
		this.patientname = patientname;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.mobile = mobile;
		this.address = address;
		this.reportlist = reportlist;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<PatientReport> getReportlist() {
		return reportlist;
	}


	public void setReportlist(List<PatientReport> reportlist) {
		this.reportlist = reportlist;
	}


	
}
