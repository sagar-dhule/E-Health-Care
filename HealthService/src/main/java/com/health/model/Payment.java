package com.health.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String doctorid;
	private double amount;
	// Other payment details like payment date, payment method, etc.

	// Constructors, getters, and setters

	public Payment() {
	}

	public Payment(String doctorid, double amount) {

		this.doctorid = doctorid;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorId() {
		return doctorid;
	}

	public void setDoctorId(String doctorid) {
		this.doctorid = doctorid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}