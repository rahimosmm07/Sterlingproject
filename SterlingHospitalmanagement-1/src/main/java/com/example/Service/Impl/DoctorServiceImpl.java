package com.example.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Doctor;
import com.example.Exception.DoctorNotFoundException;
import com.example.Repository.DoctorRepository;
import com.example.Service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public Doctor findDoctorById(int id) throws DoctorNotFoundException {
		// TODO Auto-generated method stub

		Optional<Doctor> optional = doctorRepository.findById(id);
		try {

			if (optional.isPresent()) {
				doctorRepository.findById(id);
			} else {
				throw new DoctorNotFoundException("Doctor not found with the matching ID!");
			}

		} catch (Exception e) {

			throw new DoctorNotFoundException("Doctor not found with the matching ID!");
		}
		return optional.get();
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException {
		// TODO Auto-generated method stub

		Optional<Doctor> optional = doctorRepository.findById((int) doctor.getDid());

		if (optional.isPresent()) {
			Doctor _doctor = doctorRepository.findById((int) doctor.getDid()).get();
			_doctor.setDcontact(doctor.getDcontact());
			_doctor.setDemail(doctor.getDemail());
			_doctor.setDname(doctor.getDname());
			_doctor.setDlocation(doctor.getDlocation());
			_doctor.setDspeciality(doctor.getDspeciality());
			return doctorRepository.save(_doctor);
		} else {
			throw new DoctorNotFoundException("Doctor not found with the matching ID!");
		}

		// return optional.get();
	}

	@Override
	public void deleteDoctor(int id) throws DoctorNotFoundException {
		// TODO Auto-generated method stub
		Optional<Doctor> d = doctorRepository.findById(id);

		if (d.isPresent()) {
			doctorRepository.deleteById(id);
		} else {
			throw new DoctorNotFoundException("Doctor not found with the matching ID!");
		}

	}

	@Override
	public List<Doctor> findDoctorByDlocation(String dlocation) {
		// TODO Auto-generated method stub
		return doctorRepository.findDoctorByDlocation(dlocation);

	}

}
