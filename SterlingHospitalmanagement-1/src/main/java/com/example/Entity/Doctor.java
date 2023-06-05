package com.example.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SterlinghospitalDoctor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private int did;

	@NotEmpty(message = "Name can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper name")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Name")
	private String dname;
	@NotEmpty(message = "must include email ")

	@NotBlank(message = "must type Value in email of doctor")
	@Email(message = "Invalid email of doctor")
	@Column(unique = true)
	private String demail;
	@NotEmpty(message = "Location can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper Location")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Location")
	private String dlocation;
	@NotEmpty(message = "Phone Number can't be empty!")
	@Size(min = 10, max = 10, message = "Invalid Phone Number please enter a vaild phone number minimum of 10 digits")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid input:Enter 10 digit numbers only")
	@Column(unique = true)
	private String dcontact;
	@NotEmpty(message = "Location can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper Location")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Speciality")
	private String dspeciality;
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.LAZY) // to establish O2M relation between Doctor to Appointments
	@JsonIgnore // to ignore serialization
	List<Appointment> appointments;

	@Builder
	public Doctor(String dname, String demail, String dlocation, String dcontact, String dspeciality) {
		super();

		this.dname = dname;
		this.demail = demail;
		this.dlocation = dlocation;
		this.dcontact = dcontact;
		this.dspeciality = dspeciality;
	}

}