package com.health.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.model.Appointment;
import com.health.model.Doctor;
import com.health.model.Speciality;
import com.health.payload.request.AddDoctorRequest;
import com.health.payload.response.DocAppointmentListResponse;
import com.health.payload.response.DoctorResponse;
import com.health.payload.response.SpecialityResponse;
import com.health.repository.DoctorRepository;
import com.health.repository.SpecialityRepository;
import com.health.services.DoctorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@Autowired
	SpecialityRepository specialityRepository;

	@Autowired
	DoctorRepository doctorRepository;

	// Add Doctor
	@PostMapping(value = "/addDoctor/{id}")
	@PreAuthorize("hasRole('admin')")
	public Doctor saveDoctor(@PathVariable(name = "id") String id, @ModelAttribute AddDoctorRequest req,
			@RequestParam("image") MultipartFile image) throws IOException {
		// Retrieve the speciality from the repository
		Optional<Speciality> speciality = specialityRepository.findById(id);

		System.out.println("speciality==>" + speciality);
		// Create a new Doctor object and set its properties
		Doctor doctor = new Doctor();
		doctor.setName(req.getName());
		doctor.setEmail(req.getEmail());
		doctor.setMobilenumber(req.getMobilenumber());
		doctor.setSpeciality(speciality.get());
		doctor.setQualification(req.getQualification());
		doctor.setDescription(req.getDescription());
		doctor.setCreatedOn(new Date());
		doctor.setCreatedBy(req.getCreatedBy());
		doctor.setUpdatedOn(new Date());
		doctor.setUpdatedBy(req.getUpdatedBy());

		return doctorService.saveDoctor(image, doctor);

	}

	@GetMapping("/allDoctors")
	public ResponseEntity<List<Doctor>> getDoctors()
	{
		List<Doctor> dlist = doctorService.getDoctor();
		return ResponseEntity.ok(dlist);
	}

	@GetMapping("/allDoctorsBySpeciality/{specialityId}")
	public ResponseEntity<List<DoctorResponse>> allDoctorsByName(@PathVariable String specialityId) {
		List<DoctorResponse> docRes = new ArrayList<>();
		List<Doctor> doc = doctorRepository.findAllBySpecialityId(specialityId);
		if (doc != null) {
			for (Doctor doctor : doc) {
				DoctorResponse response = new DoctorResponse();
				response.setDoctorid(doctor.getId());
				response.setDoctorname(doctor.getName());
				// Set other properties of DoctorResponse as needed
				docRes.add(response);
			}
		}
		return ResponseEntity.ok(docRes);
	}

	// update Doctor

	@PreAuthorize("hasRole('admin') ")
	@PutMapping("/Doctor/update/{id}")
	public Doctor update(AddDoctorRequest req, @PathVariable("id") String id) throws IOException {
		System.out.println("In update ");
		System.out.println("In update Id :" + id);

		Doctor p = doctorRepository.getById(id);

		System.out.println("Doctor id :" + doctorRepository.getById(id));

		Speciality c = specialityRepository.getById(p.getSpeciality().getId());
		System.out.println("Cate id" + p.getSpeciality().getId());

		p.setName(req.getName());

		p.setDescription(req.getDescription());
		p.setUpdatedOn(new Date());
		p.setUpdatedBy(req.getUpdatedBy());
		p.setSpeciality(c);

		System.out.println("Doctor : " + p);
		return p;
	}

	@GetMapping("/allSpeciality")
	public ResponseEntity<List<SpecialityResponse>> getAllDoctorsSpeciality() {
		List<SpecialityResponse> speRes = new ArrayList<>();
		List<Speciality> speciality = specialityRepository.findAll();
		Collections.sort(speciality, (s1, s2) -> s1.getTitle().compareTo(s2.getTitle()));
		if (speciality != null) {
			for (Speciality sp : speciality) {
				SpecialityResponse response = new SpecialityResponse();
				response.setId(sp.getId());
				response.setTitle(sp.getTitle());
				// Set other properties of DoctorResponse as needed
				speRes.add(response);
			}
		}
		return ResponseEntity.ok(speRes);
	}

	@GetMapping("/getAllAppointments/{doctorid}")
	public List<DocAppointmentListResponse> getAppointmentListByDoctorID(
			@PathVariable(name = "doctorid") String doctorid) {

		List<DocAppointmentListResponse> apptResponse = doctorService.getAppointmentListByDoctorID(doctorid);

		if (apptResponse != null) {
			return apptResponse;
		}
		return null;

	}
	
	@GetMapping("/getAllAppointments")
	public List<Appointment> getAllAppointments() {

		List<Appointment> apptResponse = doctorService.getAllAppointmentList();

		if (apptResponse != null) {
			return apptResponse;
		}
		return null;

	}

}
