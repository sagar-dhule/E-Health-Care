package com.health.payload.request;

public class UpdateAppointmentRequest {
	private Long id;
    private String patientid;
    private String patientname;
    private String doctorid;
    private String date;
    private String slot;
    private String status;
    private Object payment; // Adjust the type based on the structure of payment
    private String reportid;

    // Constructors
    public UpdateAppointmentRequest() {
    }

    public UpdateAppointmentRequest(Long id, String patientid, String patientname, String doctorid, String date, String slot, String status, Object payment, String reportid) {
        this.id = id;
        this.patientid = patientid;
        this.patientname = patientname;
        this.doctorid = doctorid;
        this.date = date;
        this.slot = slot;
        this.status = status;
        this.payment = payment;
        this.reportid = reportid;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPayment() {
        return payment;
    }

    public void setPayment(Object payment) {
        this.payment = payment;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }
}