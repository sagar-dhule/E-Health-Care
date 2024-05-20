package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.health.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

	//@Query(value = "SELECT * FROM prescription WHERE patientid = ?1 AND appointmentid = ?2", nativeQuery = true)
	Prescription findByPatientidAndAppointmentid(String patientid, Long appointmentid);

}
