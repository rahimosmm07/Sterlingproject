package com.example.Service;

import java.util.List;

import com.example.Entity.Appointment;
import com.example.Exception.AppointmentNotFoundException;

public interface AppointmentService {
	public Appointment addAppointment(Appointment appointment);

	public List<Appointment> getAllAppointment();

	public Appointment findAppointmentById(int id) throws AppointmentNotFoundException;

	public Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException;

	public void deleteAppointment(int id) throws AppointmentNotFoundException;
}
