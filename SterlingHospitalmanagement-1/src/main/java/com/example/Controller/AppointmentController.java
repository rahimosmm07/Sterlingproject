package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Appointment;
import com.example.Exception.AppointmentNotFoundException;
import com.example.Exception.DoctorNotFoundException;
import com.example.Service.AppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@PostMapping(path = "/appointment")
	public ResponseEntity<Object> createAppointment(@RequestBody @Valid Appointment appointment) {

		Map<String, String> errors = new HashMap<>();
		String field = null;
		String message = null;
		try {
			Appointment savedAppointment = appointmentService.addAppointment(appointment);
			return new ResponseEntity<Object>(savedAppointment, HttpStatus.CREATED);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			// System.out.println(ex.getMessage());
			// if (ex instanceof SQLIntegrityConstraintViolationException) {
//							if (((SQLException) ex).getSQLState().equals("23000")) {
			field += 23000;
//								if (ex.getMessage().contains("Duplicate")) {
			// if (ex.getMessage().contains("email")) {
//										System.out.println("Duplicate email");
			// message += "Duplicate email";
			message += "Duplication of unique field";
			//

		}
		errors.put(field, message);
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

//	@PostMapping("/appointment")
//	public Appointment createAppointment(@RequestBody Appointment appointment) {
//		return appointmentService.addAppointment(appointment);
//	}

	@GetMapping("/appointment")
	public List<Appointment> findAllAppointments() {
		return appointmentService.getAllAppointment();
	}

	@GetMapping("/appointment/{id}")
	public Appointment findAppointmentById(@PathVariable int id) throws AppointmentNotFoundException {
		return appointmentService.findAppointmentById(id);
	}

//	@GetMapping("/users/api/{name}")
//	public List<AnudipUser> findUserByUname(@PathVariable String name) {
//		return userService.findByUsername(name);
//	}
//
//	@GetMapping("/users/Email/{email}")
//	public List<AnudipUser> findUserByUseremail(@PathVariable String email) {
//		return userService.findByEmail(email);
//	}

	@DeleteMapping("/appointment/{id}")
	public String deleteAppointment(@PathVariable int id) throws AppointmentNotFoundException {

		appointmentService.deleteAppointment(id);
		return "Appointment is deleted";
	}

	@PutMapping("/appointment")
	public Appointment updateAppointment(@RequestBody Appointment appointment)
			throws AppointmentNotFoundException, DoctorNotFoundException {
		return appointmentService.updateAppointment(appointment);
	}

}
