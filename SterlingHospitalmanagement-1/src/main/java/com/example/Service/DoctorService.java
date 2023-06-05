package com.example.Service;

import java.util.List;

import com.example.Entity.Doctor;
import com.example.Exception.DoctorNotFoundException;

public interface DoctorService {
	public Doctor addDoctor(Doctor docotr);

	public List<Doctor> getAllDoctor();

	public List<Doctor> findDoctorByDlocation(String dlocation) throws DoctorNotFoundException;

	public Doctor findDoctorById(int id) throws DoctorNotFoundException;

	public Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException;

	public void deleteDoctor(int id) throws DoctorNotFoundException;

}
