import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { PrescriptionsComponent } from '../prescriptions/prescriptions.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DoctorService } from '../../services/doctor.service';
import { CustomDatePipe } from '../../custom/custom-date.pipe';
import { AppointmentconfirmdialogComponent } from '../appointmentconfirmdialog/appointmentconfirmdialog.component';
import { ViewreportsComponent } from '../viewreports/viewreports.component';

@Component({
  selector: 'app-doctordashboard',
  templateUrl: './doctordashboard.component.html',
  styleUrls: ['./doctordashboard.component.css']
})
export class DoctordashboardComponent implements OnInit {
  appointments: Appointment[] = [];
  user: any;
  userid: any;
  showPdf: boolean = false;
  pdfSrc: any = '';
  showModal: boolean = false;

  constructor(public modalService: NgbModal,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private customDatePipe: CustomDatePipe) { }

  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('auth-user') || '{}');

    this.userid = this.user?.id;


    this.getAppointments(this.userid);

  }

  getAppointments(userid: any): void {
    this.doctorService.getAppointmentByDoctorID(userid).subscribe((res: any) => {
      if (res) {
        this.appointments = res;
        console.log("his.appointments==>",this.appointments);
        

        this.appointments?.forEach((appt: any) => {
          const transformDate = this.customDatePipe.transform(appt.date);
          appt.date = transformDate;
        })
      }

    })
  }

  getLabReports(reportid: any): void {
      const modalRef = this.modalService.open(ViewreportsComponent, { backdrop: 'static' });
      modalRef.componentInstance.reportIds = reportid;
      modalRef.result.then((result: any) => {
        
         
      });
  
    }
  

  


  openPrescriptionModal(appointmentId: any, patientid: any): void {
    const modalRef = this.modalService.open(PrescriptionsComponent, { backdrop: 'static' });
    modalRef.componentInstance.appointmentId = appointmentId;
    modalRef.componentInstance.patientid = patientid;
    modalRef.result.then((result: any) => {
      if (result) {

      }
    });
  }

  openConfirmationDialog(appointment: any): void {
    const modalRef = this.modalService.open(AppointmentconfirmdialogComponent, { backdrop: 'static' });
    modalRef.componentInstance.appointment = appointment;
    modalRef.result.then((result: any) => {
      if (result==='confirmed') {
        console.log("result=>",result);
        appointment.status = 'confirmed';

        console.log("appointment=>",appointment);
        
        this.patientService.updateAppointment(appointment).subscribe((res: any) => {
          if(res){
            this.getAppointments(this.userid);
          }
          
       });
      }
    });

  }

  getStatusColor(status: any): any {
    return status === 'pending' ? 'red' : status === 'confirmed' ? 'green' : 'green';
  }
}

interface Appointment {
  id: any;
  patientid: any;
  patientname: any;
  date: any;
  slot: any;
  status: any;
  payment: any;
  reports: any[];
}

interface Prescription {
  patientName: string;
  prescription: string;
}

interface LabReport {
  patientName: string;
  testType: string;
  results: string;
}

