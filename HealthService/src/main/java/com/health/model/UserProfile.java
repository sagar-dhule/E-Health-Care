package com.health.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Size(max = 50)
	private String Firstname;

	@Size(max = 50)
	private String Lastname;

	@Size(max = 50)
	private String Address;

	@Size(max = 50)
	private String Gender;

	@Size(max = 50)
	private String mobile;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfile(String id, @Size(max = 50) String firstname, @Size(max = 50) String lastname,
			@Size(max = 50) String address, @Size(max = 50) String gender, @Size(max = 50) String mobile) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;
		Address = address;
		Gender = gender;
		this.mobile = mobile;
	}

	public UserProfile(String id, @Size(max = 50) String firstname, @Size(max = 50) String lastname, String address,
			@Size(max = 50) String gender, @Size(max = 50) String mobile, User user) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;

		Address = address;
		Gender = gender;
		this.mobile = mobile;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}


	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}


	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
