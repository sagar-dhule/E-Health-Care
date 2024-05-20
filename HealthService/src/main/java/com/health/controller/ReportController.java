package com.health.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.model.Report;
import com.health.payload.response.ReportMessage;
import com.health.payload.response.ReportResponseFile;
import com.health.repository.ReportRepository;
import com.health.services.ReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private ReportRepository reportRepository;

	@PostMapping("/upload")
	public ReportMessage uploadFile(@RequestParam("patientid") String patientid,
			@RequestParam("appointmentid") Long appointmentid, @RequestParam("reportname") String reportname,
			@RequestParam("file") MultipartFile file) {

		ReportMessage res = new ReportMessage();
		String message = "";
		try {
			reportService.saveReport(patientid, appointmentid, reportname, file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();

			res.setMessage(message);
			return res;
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			res.setMessage(message);
			return res;
		}
	}

	@GetMapping("/file/{patientid}/{appointmentid}")
	public ResponseEntity<ReportResponseFile> getFile(@PathVariable String patientid,
			@PathVariable Long appointmentid) {
		ReportResponseFile report = reportService.getFile(patientid, appointmentid);

		return ResponseEntity.ok(report);
	}

	@GetMapping("/getreport/{patientid}/{appointmentid}")
	public ResponseEntity<byte[]> exportToPDF(@PathVariable String patientid, @PathVariable Long appointmentid

	) {

		Optional<Report> r =Optional.ofNullable(reportRepository.findByPatientidAndAppointmentid(patientid, appointmentid));

		if (r.isPresent()) {
			Report report = r.get();
			byte[] pdfData = report.getData();

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + report.getName() + ".pdf\"")
					.contentType(MediaType.APPLICATION_PDF).body(pdfData);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getLabReport/{reportid}")
	public ResponseEntity<byte[]> exportToPDF(@PathVariable String reportid) {
	    Optional<Report> optionalReport = reportRepository.findById(reportid);

	    if (optionalReport.isPresent()) {
	        Report report = optionalReport.get();
	        byte[] pdfData = report.getData();

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + report.getName() + ".pdf\"")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfData);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


}
