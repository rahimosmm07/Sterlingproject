package com.example.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "Sterlinghospitalappointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aid;
	@NotEmpty(message = "Name can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper name")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Name")
	private String pname;
	@NotEmpty(message = "must include email ")

	@NotBlank(message = "must type Value in email of doctor")
	@Email(message = "Invalid email of doctor")
	@Column(unique = true)
	private String pemail;
	@NotEmpty(message = "Location can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper Location")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Location")
	private String plocation;
	@Column(unique = true)
	@NotEmpty(message = "Phone Number can't be empty!")
	@Size(min = 10, max = 10, message = "Invalid Phone Number please enter a vaild phone number minimum of 10 digits")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid input:Enter 10 digits numbers only")
	private String pcontact;
	@NotEmpty(message = "Location can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper speciality")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the speciality")
	private String speciality;
	private String appointmentdate;
	@ManyToOne(cascade = CascadeType.ALL) // to establish M2O relation between Appointments to Doctor
	@JsonIgnore // to ignore serialization
	Doctor dr;

	@Builder
	public Appointment(long aid, String pname, String pemail, String plocation, String pcontact, String speciality,

			String appointmentdate, Doctor doctor) {
		super();
		this.aid = (int) aid;
		this.pname = pname;
		this.pemail = pemail;
		this.plocation = plocation;
		this.pcontact = pcontact;
		this.speciality = speciality;
		this.appointmentdate = appointmentdate;
		this.dr = doctor;
	}

	public Appointment(String pname, String pemail, String plocation, String pcontact, String speciality,
			String appointmentdate, Doctor dr) {
		super();
		this.pname = pname;
		this.pemail = pemail;
		this.plocation = plocation;
		this.pcontact = pcontact;
		this.speciality = speciality;
		this.appointmentdate = appointmentdate;
		this.dr = dr;
	}

}
