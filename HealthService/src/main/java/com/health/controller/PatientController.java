package com.health.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.model.Appointment;
import com.health.model.Prescription;
import com.health.model.Reviews;
import com.health.payload.request.UpdateAppointmentRequest;
import com.health.payload.response.AppointmentResponse;
import com.health.payload.response.Patient;
import com.health.services.PatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	PatientService patientService;

	@PostMapping("/bookNewAppointment")
	public ResponseEntity<Appointment> addNewAppointment(@RequestBody Appointment appointment) throws Exception {

		Appointment app = patientService.addNewAppointment(appointment);

		return new ResponseEntity<Appointment>(app, HttpStatus.OK);
	}

	@PutMapping("/updateAppointment")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody UpdateAppointmentRequest appointment) {
		try {
			Appointment updatedAppointment = patientService.updateAppointment(appointment);
			return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAppointmentsList/{patientid}")
	public List<AppointmentResponse> getAppointmentListByPatientId(@PathVariable(name = "patientid") String patientid) {

		List<AppointmentResponse> apptResponse = patientService.getAppointmentListByPatientId(patientid);

		if (apptResponse != null) {
			return apptResponse;
		}
		return null;

	}

	@PostMapping("save/reviews")
	public Map<String, Object> saveReview(@RequestBody Reviews review) {
		Map<String, Object> map = null;
		if (review.getPatientid() == null || review.getDoctorid() == null || review.getSpecialityid() == null
				|| review.getReviewtext() == null) {
			return map;
		}

		map = patientService.saveReview(review);

		if (map.get("id") != null) {
			return map;
		} else {
			return map;
		}
	}

	@GetMapping("/allPatients")
	public ResponseEntity<List<Patient>> getAllPatientList() {
	    List<Patient> patientList = patientService.getAllPatientList();
	    if (patientList != null) {
	        return ResponseEntity.ok(patientList);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/getprescription/{patientid}/{appointmentid}")
	public ResponseEntity<Prescription> getPatientPresecription(@PathVariable(name = "patientid") String patientid,
			@PathVariable(name = "appointmentid") Long appointmentid) {
		Prescription prescription = patientService.getPatientPresecription(patientid,appointmentid);
	    if (prescription != null) {
	        return ResponseEntity.ok(prescription);
	    } else {
	        return ResponseEntity.ok(prescription);
	    }
	}
}
