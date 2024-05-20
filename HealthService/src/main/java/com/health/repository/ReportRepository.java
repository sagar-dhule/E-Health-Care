package com.health.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.model.Report;
import com.health.model.User;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {

	Report findByPatientidAndAppointmentid(String patientid, Long appointmentid);

	List<Report> findByPatientid(String id);
}

