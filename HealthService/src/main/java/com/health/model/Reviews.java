package com.health.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="reviews")
public class Reviews {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String patientid;
    private String doctorid; 
    private String specialityid;
    private String reviewtext;
    
	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(String patientid, String doctorid, String specialityid,
			String reviewtext) {
		super();
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.specialityid = specialityid;
		this.reviewtext = reviewtext;
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

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}

	public String getReviewtext() {
		return reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}

	public String getId() {
		return id;
	}
    
    
    
}
