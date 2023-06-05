package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
