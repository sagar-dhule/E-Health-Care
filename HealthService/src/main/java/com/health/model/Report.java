package com.health.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "reports")
public class Report {
	@Id
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  private String id;
	  private String patientid;
	  private Long appointmentid;

	  private String name;

	  private String type;

	  @Lob
	  @Type(type="org.hibernate.type.BinaryType")
	  @Column(name = "data")
	  private byte[] data;

	  public Report() {
	  }

	  public Report(String patientid, Long appointmentid,String name, String type, byte[] data) {
		  this.patientid=patientid;
		  this.appointmentid=appointmentid;
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }

	  public String getId() {
	    return id;
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

	public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getType() {
	    return type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }

	
}
