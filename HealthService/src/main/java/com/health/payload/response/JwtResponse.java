package com.health.payload.response;


public class JwtResponse {

	private String email;
	private String firstname;
	private String lastname;
	private String type;
	private String token;
	private String id;
	private String token_type = "Bearer";

	public JwtResponse(String token, String email, String type) {
		super();
		this.token = token;
		this.email = email;
		this.type = type;
	}
	
	



	public JwtResponse(String id , String email, String firstname, String lastname, 
			String type, String token) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
		this.token = token;
	}





	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}





	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
