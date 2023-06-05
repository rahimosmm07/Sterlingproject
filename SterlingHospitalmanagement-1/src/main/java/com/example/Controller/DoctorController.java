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

import com.example.Entity.Doctor;
import com.example.Exception.DoctorNotFoundException;
import com.example.Service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@GetMapping("/posted")
	public String postAdd() {
		return "Hello";
	}

	@GetMapping("/auth")
	public String authAdd() {
		return "auth";
	}

	@PostMapping(path = "/doctor")
	public ResponseEntity<Object> createDoctor(@RequestBody @Valid Doctor doctor) {
		Map<String, String> errors = new HashMap<>();
		String field = null;
		String message = null;
		try {
			Doctor savedDoctor = doctorService.addDoctor(doctor);
			return new ResponseEntity<Object>(savedDoctor, HttpStatus.CREATED);
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
			// if (ex instanceof SQLIntegrityConstraintViolationException) {
//				if (((SQLException) ex).getSQLState().equals("23000")) {
			field += 23000;
//					if (ex.getMessage().contains("Duplicate")) {
			// if (ex.getMessage().contains("email")) {
//							System.out.println("Duplicate email");
			// message += "Duplicate email";
			message += "Duplication of unique field";
//
			// }
//					}
//				}
			// }
			errors.put(field, message);
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/doctors")
	public List<Doctor> findAllDoctors() {
		return doctorService.getAllDoctor();
	}

	@GetMapping("/doctorlocation/{location}")
	public List<Doctor> findAllDoctorsByLocation(@PathVariable String location) throws DoctorNotFoundException {
		return doctorService.findDoctorByDlocation(location);
	}

	@GetMapping("/doctor/{id}")
	public Doctor findDoctorById(@PathVariable int id) throws DoctorNotFoundException {
		return doctorService.findDoctorById(id);
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

	@DeleteMapping("/doctor/{id}")
	public String deleteDoctor(@PathVariable int id) throws DoctorNotFoundException {

		doctorService.deleteDoctor(id);
		return "Doctor is deleted";
	}

	@PutMapping("/doctor")
	public Doctor updateDoctor(@RequestBody Doctor doctor) throws DoctorNotFoundException {
		return doctorService.updateDoctor(doctor);
	}

}
