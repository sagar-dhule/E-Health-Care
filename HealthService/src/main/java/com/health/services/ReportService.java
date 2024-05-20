package com.health.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.health.model.Report;
import com.health.payload.response.ReportResponseFile;
import com.health.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public Report saveReport(String patientid, Long appointmentid, String reportname, MultipartFile file)
			throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Report r = new Report(patientid, appointmentid, reportname, file.getContentType(), file.getBytes());

		return reportRepository.save(r);
	}

	public ReportResponseFile getFile(String patientid, Long appointmentid) {

		Report r = reportRepository.findByPatientidAndAppointmentid(patientid, appointmentid);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(r.getId())
				.toUriString();

		return new ReportResponseFile(r.getName(), fileDownloadUri, r.getType(), r.getData().length);

	}

}
