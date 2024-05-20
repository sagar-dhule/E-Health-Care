package com.health.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByEmail(String email);

	List<Appointment> findBySlot(String slot);

	List<Appointment> findByDoctorname(String doctorname);

	List<Appointment> findByPatientid(String patientid);

	List<Appointment> findByDoctorid(String doctorid);

	Optional<Appointment> findByIdAndPatientid(Long id, String patientid);

}
