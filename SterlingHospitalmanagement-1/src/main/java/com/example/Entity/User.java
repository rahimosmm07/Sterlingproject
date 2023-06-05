package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	private int userid;
	@NotEmpty(message = "UserName can't be empty!")
	@Size(min = 4, max = 20, message = "Please enter proper user name")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Name")
	private String username;
	private String password;
	@NotEmpty(message = "role can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper role")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the role")
	private String role;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
