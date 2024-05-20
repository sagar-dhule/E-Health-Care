package com.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.payload.request.PrescriptionRequest;
import com.health.payload.response.PrescriptionResponse;
import com.health.services.PrecriptionService;

@RestController
@RequestMapping("/prescription")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PrecriptionController {
	
	@Autowired
	PrecriptionService prescriptionService;
	
	@GetMapping("/getprescription/{patientid}/{appointmentid}")
	public PrescriptionResponse getPricriptions(@PathVariable(name="patientid") String patientid,
			@PathVariable(name="appointmentid") Long appointmentid) {
		
		PrescriptionResponse prescriptionResponse= prescriptionService.getPrescriptions(patientid,appointmentid);
		return prescriptionResponse;
		
	}
	
	
	@PostMapping("/save")
    public Map<String, String> savePrescriptions(@RequestBody PrescriptionRequest request) {
        return prescriptionService.savePrescription(request);
    }

}
