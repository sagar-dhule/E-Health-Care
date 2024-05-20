package com.health.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.model.Prescription;
import com.health.payload.request.PrescriptionRequest;
import com.health.payload.response.PrescriptionResponse;
import com.health.repository.PrescriptionRepository;

@Service
public class PrecriptionService {

	@Autowired
	PrescriptionRepository prescriptionRepository;

	public PrescriptionResponse getPrescriptions(String patientId, Long appointmentId) {
		Prescription p = prescriptionRepository.findByPatientidAndAppointmentid(patientId, appointmentId);

		PrescriptionResponse res = new PrescriptionResponse();
		if (p != null) {
			res.setId(p.getId());
			res.setPrescription(p.getPrescription());
		}
		return res;
	}

	public Map<String, String> savePrescription(PrescriptionRequest request) {
        Prescription p = prescriptionRepository.findByPatientidAndAppointmentid(request.getPatientid(), request.getAppointmentid());
        String status;

        if (p != null) {
            p.setPrescription(request.getPrescription());
            prescriptionRepository.save(p);
            status = "Prescription updated";
        } else {
            Prescription newPrescription = new Prescription();
            newPrescription.setPatientid(request.getPatientid());
            newPrescription.setAppointmentid(request.getAppointmentid());
            newPrescription.setPrescription(request.getPrescription());
            prescriptionRepository.save(newPrescription);
            status = " Prescription saved";
        }
        
        Map<String , String> map = new HashMap<>();
        map.put("status", status + " successfully");
        return map;
    }
}
