package com.health.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.health.model.Appointment;
import com.health.model.Doctor;
import com.health.model.Report;
import com.health.model.Speciality;
import com.health.model.User;
import com.health.payload.response.DocAppointmentListResponse;
import com.health.payload.response.PatientReport;
import com.health.repository.AppointmentRepository;
import com.health.repository.DoctorRepository;
import com.health.repository.ReportRepository;
import com.health.repository.SpecialityRepository;
import com.health.repository.UserRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	SpecialityRepository specialityRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AppointmentRepository appointmentsRepository;

	@Autowired
	ReportRepository reportRepository;

	Doctor Doctor;

	public static String UploadDir = System.getProperty("user.dir") + "/src/main/imagedata";

//Add Doctor

	public Doctor saveDoctor(@RequestParam MultipartFile image, Doctor Doctor) {
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userRepository.findByEmail(email);
		Doctor.setCreatedBy(u.getId());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}
		try {
			String base64String = Base64.getEncoder().encodeToString(image.getBytes());

			Doctor.setImage(base64String);
		} catch (IOException e) {
			e.printStackTrace();
		}
		doctorRepository.save(Doctor);
		return Doctor;
	}

	public List<Doctor> getDoctor() {
		return doctorRepository.findAll();
	}

	public Page<Doctor> getDoctorByCategoryId(String category_id, String title, String keyword, Integer pageNo,
			Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, org.springframework.data.domain.Sort.by(sortBy));

		return doctorRepository.findAllBySpecialityId(category_id, title, keyword, paging);
	}

	public void updateDoctor(MultipartFile image, Doctor Doctor, String id) throws IOException {
		System.out.println("Service");
		if (image == null) {
			System.out.println("If loop");
			Doctor.setImage(Doctor.getImage());

			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			User u = userRepository.findByEmail(email);
			Doctor.setUpdatedBy(u.getId());
			System.out.println(Doctor);

			doctorRepository.save(Doctor);

		} else {

			// String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			String base64String = Base64.getEncoder().encodeToString(image.getBytes());
			Doctor.setImage(base64String);

			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			User u = userRepository.findByEmail(email);
			Doctor.setUpdatedBy(u.getId());

			System.out.println("***********************Service************************");
			System.out.println(Doctor);

			doctorRepository.save(Doctor);
		}
	}

	public void deleteDoctor(String id) {

		doctorRepository.deleteById(id);
		;

	}

	public List<DocAppointmentListResponse> getAppointmentListByDoctorID(String doctorid) {

		List<DocAppointmentListResponse> apptResList = new ArrayList<>();
		List<Appointment> apptResponse = appointmentsRepository.findByDoctorid(doctorid);

		for (Appointment appt : apptResponse) {
			DocAppointmentListResponse appointmentRes = new DocAppointmentListResponse();

			appointmentRes.setId(appt.getId());
			appointmentRes.setPatientid(appt.getPatientid());
			appointmentRes.setPatientname(appt.getPatientname());
			appointmentRes.setDoctorid(appt.getDoctorid());
			appointmentRes.setDate(appt.getStartdate());

			appointmentRes.setSlot(appt.getSlot());
			appointmentRes.setStatus(appt.getAppointmentstatus());
			appointmentRes.setPayment(appt.getPaymentstatus());

			List<PatientReport> reportList = new ArrayList<>();
			List<Report> r = reportRepository.findByPatientid(appt.getPatientid());
			for (Report report : r) {

				PatientReport pr = new PatientReport();
				pr.setId(report.getId());
				pr.setReportname(report.getName());
				reportList.add(pr);
			}

			appointmentRes.setReports(reportList);

			apptResList.add(appointmentRes);

		}
		return apptResList;

	}

	public List<Appointment> getAllAppointmentList() {
		// TODO Auto-generated method stub
		return appointmentsRepository.findAll();
	}

	public void saveDoctorUser(User user) {

		Speciality speciality = specialityRepository.findById(user.getSpecialization()).get();

		Doctor doctor = new Doctor();
		doctor.setId(user.getId());
		doctor.setName(user.getFirstname() + " " + user.getLastname());
		doctor.setEmail(user.getEmail());
		doctor.setMobilenumber(user.getMobile());
		doctor.setSpeciality(speciality);
		doctor.setQualification(speciality.getTitle());

		doctor.setDescription("");
		doctor.setCreatedOn(new Date());
		doctor.setCreatedBy(user.getId());
		doctor.setUpdatedOn(new Date());
		doctor.setUpdatedBy(user.getFirstname());

		doctorRepository.save(doctor);

	}

}
