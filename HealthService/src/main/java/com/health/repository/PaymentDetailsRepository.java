package com.health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.model.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, String> {

	List<PaymentDetails> findByPatientid(String patientId);

	List<PaymentDetails> findByDoctorid(String doctorId);
	
	boolean existsByPatientidAndDoctoridAndAppointmentid(String patientId, String doctorId, Long appointmentId);
}
