package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.model.Payment;
import com.health.model.PaymentDetails;
import com.health.repository.PaymentDetailsRepository;
import com.health.repository.PaymentRepository;
import com.health.services.PatientService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;
	
	@Autowired
	PatientService patientService;

	
	@PostMapping("/save")
	public ResponseEntity<PaymentDetails> savePayment(@RequestBody PaymentDetails payment) {
	    try {
	        if (payment == null) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	        if (paymentDetailsRepository.existsByPatientidAndDoctoridAndAppointmentid(
	                payment.getPatientid(), payment.getDoctorid(), payment.getAppointmentid())) {
	            // Payment details already exist for this combination
	            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	        }

	   
	        PaymentDetails savedPayment = paymentDetailsRepository.save(payment);
	        if (savedPayment != null) {
	            patientService.updateAppointmentPaymentStatus(savedPayment.getPatientid(), savedPayment.getAppointmentid());
	            return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@GetMapping("/getByPatientId/{patientId}")
	public ResponseEntity<List<PaymentDetails>> getPaymentsByPatientId(@PathVariable String patientId) {
		try {
			List<PaymentDetails> payments = paymentDetailsRepository.findByPatientid(patientId);
			if (payments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getByDoctorId/{doctorId}")
	public ResponseEntity<Payment> getPaymentsByDoctorId(@PathVariable String doctorId) {
		try {
			Payment payments = paymentRepository.findByDoctorid(doctorId);
			if (payments!=null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
