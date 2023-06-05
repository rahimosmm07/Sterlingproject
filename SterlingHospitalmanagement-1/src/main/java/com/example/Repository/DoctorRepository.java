package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	public List<Doctor> findDoctorByDlocation(String dlocation);

}
