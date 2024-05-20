package com.health.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.model.Appointment;
import com.health.model.Payment;
import com.health.model.Prescription;
import com.health.model.Report;
import com.health.model.Reviews;
import com.health.model.User;
import com.health.payload.request.UpdateAppointmentRequest;
import com.health.payload.response.AppointmentResponse;
import com.health.payload.response.Patient;
import com.health.payload.response.PatientReport;
import com.health.repository.AppointmentRepository;
import com.health.repository.PaymentRepository;
import com.health.repository.PrescriptionRepository;
import com.health.repository.ReportRepository;
import com.health.repository.ReviewRepository;
import com.health.repository.UserRepository;

@Service
public class PatientService {

	@Autowired
	private AppointmentRepository appointmentsRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;

	public Appointment addNewAppointment(Appointment appointment) {
		// Check if the appointment and patientId are not null
		if (appointment == null || appointment.getPatientid() == null) {
			throw new IllegalArgumentException("Invalid appointment data");
		}

		Optional<User> user = userRepository.findById(appointment.getPatientid());
		if (!user.isPresent()) {
			throw new IllegalArgumentException("Patient does not exist");
		}

		return appointmentsRepository.save(appointment);
	}

	public List<Appointment> findPatientByEmail(String email) {
		return (List<Appointment>) appointmentsRepository.findByEmail(email);
	}

	public List<Appointment> findPatientBySlot(String slot) {
		return (List<Appointment>) appointmentsRepository.findBySlot(slot);
	}

	public List<Appointment> findPatientByDoctorName(String doctorname) {
		return (List<Appointment>) appointmentsRepository.findByDoctorname(doctorname);
	}

	public List<Appointment> getAllPatients() {
		return (List<Appointment>) appointmentsRepository.findAll();
	}

	public List<AppointmentResponse> getAppointmentListByPatientId(String patientid) {

		List<AppointmentResponse> apptResList = new ArrayList<>();
		List<Appointment> apptResponse = appointmentsRepository.findByPatientid(patientid);

		for (Appointment appt : apptResponse) {
			AppointmentResponse appointmentRes = new AppointmentResponse();

			appointmentRes.setId(appt.getId());
			appointmentRes.setPatientid(appt.getPatientid());
			appointmentRes.setPatientname(appt.getPatientname());
			appointmentRes.setDoctorid(appt.getDoctorid());
			appointmentRes.setDoctorname(appt.getDoctorname());
			appointmentRes.setDate(appt.getStartdate());

			appointmentRes.setSlot(appt.getSlot());
			appointmentRes.setStatus(appt.getAppointmentstatus());
			String paymentStatus = appt.getPaymentstatus();
			appointmentRes
					.setPayment(paymentStatus != null && !paymentStatus.trim().isEmpty() ? paymentStatus : "pending");
			appointmentRes.setReportid("");

			Payment p = paymentRepository.findByDoctorid(appt.getDoctorid());
			if (p != null) {
				appointmentRes.setAmount(p.getAmount());
			}

			apptResList.add(appointmentRes);

		}
		return apptResList;

	}

	public Map<String, Object> saveReview(Reviews review) {

		Map<String, Object> map = new HashMap<>();
		if (review.getPatientid() == null || review.getDoctorid() == null || review.getSpecialityid() == null
				|| review.getReviewtext() == null) {

			map.put("id", null);
			return map;
		}

		Reviews savedReview = reviewRepository.save(review);

		if (savedReview != null && savedReview.getId() != null) {
			map.put("id", savedReview.getId());
			return map;
		} else {
			map.put("id", null);
			return map;
		}
	}

	public Appointment updateAppointment(UpdateAppointmentRequest appointment) {

		if (appointment != null && appointment.getId() != null) {
			Appointment appt = appointmentsRepository
					.findByIdAndPatientid(appointment.getId(), appointment.getPatientid()).get();
			if (appt != null) {

				System.out.println("appt==>" + appt);
				appt.setAppointmentstatus(appointment.getStatus());
				appointmentsRepository.save(appt);
			}
		}
		return null;
	}

	public List<Patient> getAllPatientList() {
		List<User> userList = userRepository.findAll();
		List<Patient> patientList = new ArrayList<>();

		if (userList != null) {
			for (User u : userList) {
				if ("ROLE_patient".equals(u.getType())) {
					Patient p = new Patient();
					p.setPatientid(u.getId());
					p.setPatientname(u.getFirstname() + " " + u.getLastname());
					p.setEmail(u.getEmail());
					p.setAddress(u.getAddress());
					p.setAge(u.getAge());
					p.setMobile(u.getMobile());
					p.setGender(u.getGender());

					List<PatientReport> reportList = new ArrayList<>();
					List<Report> r = reportRepository.findByPatientid(u.getId());
					for (Report report : r) {

						PatientReport pr = new PatientReport();
						pr.setId(report.getId());
						pr.setReportname(report.getName());
						reportList.add(pr);
					}

					p.setReportlist(reportList);

					patientList.add(p);
				}
			}
		}

		return patientList;
	}

	public void updateAppointmentPaymentStatus(String patientid, Long appointmentid) {
		Appointment appt = appointmentsRepository.findByIdAndPatientid(appointmentid, patientid).get();
		if (appt != null) {
			appt.setPaymentstatus("paid");
			appointmentsRepository.save(appt);
		}

	}

	public Prescription getPatientPresecription(String patientid, Long appointmentid) {

		return prescriptionRepository.findByPatientidAndAppointmentid(patientid, appointmentid);

	}

}
