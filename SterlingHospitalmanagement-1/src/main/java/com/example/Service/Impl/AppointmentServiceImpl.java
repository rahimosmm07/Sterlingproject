package com.example.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Appointment;
import com.example.Exception.AppointmentNotFoundException;
import com.example.Repository.AppointmentRepository;
import com.example.Service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> getAllAppointment() {
		// TODO Auto-generated method stub
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment findAppointmentById(int id) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub

		Optional<Appointment> optional = appointmentRepository.findById(id);

		if (optional.isPresent()) {
			return appointmentRepository.findById(id).get();
		} else {
			throw new AppointmentNotFoundException("Appointment not found with the matching ID!");
		}
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Optional<Appointment> optional = appointmentRepository.findById(appointment.getAid());

		if (optional.isPresent()) {
			Appointment _appointment = appointmentRepository.findById(appointment.getAid()).get();
			_appointment.setPcontact(appointment.getPcontact());
			_appointment.setPemail(appointment.getPemail());
			_appointment.setPname(appointment.getPname());
			_appointment.setPlocation(appointment.getPlocation());
			_appointment.setSpeciality(appointment.getSpeciality());
			return appointmentRepository.save(_appointment);
		} else {
			throw new AppointmentNotFoundException("Appointment not found with the matching ID!");
		}
	}

	@Override
	public void deleteAppointment(int id) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub

		Optional<Appointment> e = appointmentRepository.findById(id);

		if (e.isPresent()) {
			appointmentRepository.deleteById(id);
		} else {
			throw new AppointmentNotFoundException("Appointment not found with the matching ID!");
		}

	}

}
