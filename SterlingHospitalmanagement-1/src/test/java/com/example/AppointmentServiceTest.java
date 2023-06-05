package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Entity.Appointment;
import com.example.Entity.Doctor;
import com.example.Exception.AppointmentNotFoundException;
import com.example.Repository.AppointmentRepository;
import com.example.Service.AppointmentService;

@SpringBootTest
public class AppointmentServiceTest {
	@Autowired
	AppointmentService isvc;

	@MockBean
	AppointmentRepository adao;
	private Doctor doctor;
	private Appointment appointment, appointment11;

	@BeforeEach
	void init() {

		doctor = doctor.builder().dname("Supriya").demail("Supriya@gmail.com").dlocation("ahmedabad")
				.dcontact("989841277").dspeciality("pediatric").build();

		appointment = appointment.builder().aid(101).pname("Sonu").pemail("sonu@gmail.com").plocation("ahmedabad")
				.pcontact("7845652111").speciality("pediatric").appointmentdate("25-05-2023").doctor(doctor).build();
		appointment11 = appointment.builder().aid(102).pname("Sneha").pemail("sneha@gmail.com").plocation("ahmedabad")
				.pcontact("7545652111").speciality("pediatric").appointmentdate("26-05-2023").doctor(doctor).build();

	}

	@Test
	void testAddAppointment() {

		// Appointment s = new Appointment(101, "Saumil", "s@gmail.com", "Pune",
		// "9898444666", "orthopedic", "05-20-2023");
		Mockito.when(adao.save(appointment)).thenReturn(appointment);
		assertEquals(appointment, isvc.addAppointment(appointment));
	}

	@Test
	void testGetAppointment() throws AppointmentNotFoundException {
		Optional<Appointment> appointment1 = Optional.of(appointment);

		Mockito.when(adao.findById(101)).thenReturn(appointment1);

		assertEquals("Sonu", isvc.findAppointmentById(101).getPname());
	}

	@Test
	void testGetAll() {
		List<Appointment> lappointment = Stream.of(appointment, appointment11).collect(Collectors.toList());
		Mockito.when(adao.findAll()).thenReturn(lappointment);

		assertEquals(2, isvc.getAllAppointment().size());
	}

	@Test
	void testDeleteAppointment() {

		try {
			isvc.deleteAppointment(102);
			Mockito.verify(adao).deleteById(102);
		} catch (AppointmentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//

	}

}
