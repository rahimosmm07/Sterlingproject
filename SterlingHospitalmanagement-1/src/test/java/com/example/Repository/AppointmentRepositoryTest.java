package com.example.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.Entity.Appointment;
import com.example.Entity.Doctor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppointmentRepositoryTest {
	// Don't replace the application default DataSource.
	@Autowired
	private TestEntityManager testEntityManager;

//	@Test
//	void testUpdateAStudent() {
//		Student s = studentRepository.findById(11).get();
//		s.setEmail("a@hotmail.com");
//		s.setName("Mahesh");
//		testEntityManager.persist(s);
//		Student sUpdated = studentRepository.findById(11).get();
//
//		assertThat(sUpdated.getEmail()).isEqualTo("a@hotmail.com");
//	}

	@Autowired
	private AppointmentRepository appointmentRepository;

	private Doctor doctor;
	private Appointment appointment;

//	@Test
//	void getAllAppointmentTest() {
//		doctor = doctor.builder().dname("Supriya").demail("Supriya@gmail.com").dlocation("ahmedabad")
//				.dcontact("989841277").dspeciality("pediatric").build();
//
//		appointment = appointment.builder().Aid(11).pname("Sonu").pemail("sonu@gmail.com").plocation("ahmedabad")
//				.pcontact("7845652111").speciality("pediatric").appointmentdate("25-05-2023").doctor(doctor).build();
//
//		List<Appointment> appointmens = appointmentRepository.findAll();
//		assertThat(appointmens.size()).isGreaterThan(0);
//	}

	@Test
	void saveAppointmentTest() {
		doctor = doctor.builder().dname("Supriya").demail("Supriya@gmail.com").dlocation("ahmedabad")
				.dcontact("989841277").dspeciality("pediatric").build();

		appointment = appointment.builder().aid(11).pname("Sonu").pemail("sonu@gmail.com").plocation("ahmedabad")
				.pcontact("7845652111").speciality("pediatric").appointmentdate("25-05-2023").doctor(doctor).build();
		testEntityManager.persist(new Appointment(11, "Sonu", "sonu@gmail.com", "ahmedabad", "7845652111", "pediatric",
				"25-05-2023", doctor));
		Appointment ap = appointmentRepository.findById(11).get();
		assertEquals(ap.getPname(), "Sonu");
	}

	@Test
	void updateAppointmentTest() {
		testEntityManager.persist(new Appointment(11, "Sonu", "sonu@gmail.com", "ahmedabad", "7845652111", "pediatric",
				"25-05-2023", doctor));
		Appointment a1 = appointmentRepository.findById(11).get();
		a1.setPname("Sejal");
		a1.setPemail("sejal@gmail.com");
		testEntityManager.persist(a1);
		assertEquals(a1.getPname(), "Sejal");
	}

	@Test
	@DisplayName("Negative Test Case")
	void updateCustomerTestNegative() {
		testEntityManager.persist(new Appointment(11, "Sonu", "sonu@gmail.com", "ahmedabad", "7845652111", "pediatric",
				"25-05-2023", doctor));
		Appointment a1 = appointmentRepository.findById(11).get();
		a1.setPname("Sejal");
		a1.setPemail("sejal@gmail.com");
		testEntityManager.persist(a1);
		assertNotEquals(a1.getPname(), "Sejal");
	}

}
