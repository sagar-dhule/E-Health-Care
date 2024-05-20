import { Component, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { PatientService } from '../../services/patient.service';
import { User } from '../../models/user';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorageService } from '../../services/token-storage.service';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { DoctorService } from '../../services/doctor.service';
import { Appointment } from '../../models/appointment';
import { BookappointmentComponent } from '../bookappointment/bookappointment.component';
import { DataBindingDirective } from '@progress/kendo-angular-grid';
import { process } from '@progress/kendo-data-query';
import { LabReportComponent } from '../labreport/labreport.component';
import { CustomDatePipe } from '../../custom/custom-date.pipe';
import { LabreportsService } from '../../services/labreports.service';
import { PrescriptionsComponent } from '../prescriptions/prescriptions.component';
import { ViewprescriptionComponent } from '../viewprescription/viewprescription.component';


@Component({
  selector: 'app-patientdashboard',
  templateUrl: './patientdashboard.component.html',
  styleUrl: './patientdashboard.component.css'
})
export class PatientdashboardComponent {

  showBookAppointmentModal: boolean = false;
  appointments: any[] = [];
  user: any;
  patientid: any;
  pdfData: any;
  showPdf: boolean = false;
  pdfSrc: any = '';
  showModal: boolean = false;
  prescription: any;

  constructor(private patientService: PatientService,
    private sanitizer: DomSanitizer,
    private labService: LabreportsService,
    private customDatePipe: CustomDatePipe,
    public modalService: NgbModal,
  ) { }


  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('auth-user') || '{}');

    this.patientid = this.user?.id;
    this.patientService.appointmentAdded$.subscribe(() => {

      console.log('New appointment added');
      this.getAppointmentList();

    });

    this.getAppointmentList();

  }

  getAppointmentList() {
    this.patientService.getAppointmentList(this.patientid).subscribe((res: any) => {
      if (res) {
        this.appointments = res;

        this.appointments?.forEach((appt: any) => {
         
          
          this.patientService.getPrescription(appt.patientid, appt.id).subscribe((prescription: any) => {
            if (prescription) {
              appt.prescription = prescription?.prescription;
            }
          });
          const transformDate = this.customDatePipe.transform(appt.date);
          appt.date = transformDate;
        })
      }

    })
  }

  openBookAppointmentModal() {
    this.showBookAppointmentModal = true;
    const modalRef = this.modalService.open(BookappointmentComponent, { backdrop: 'static' });
    modalRef.componentInstance.userId = this.user?.id;
    modalRef.result.then((result: any) => {
      if (result) {

      }
    })
  }

  // Method to close the modal for booking appointment
  closeBookAppointmentModal() {
    this.showBookAppointmentModal = false;
  }


  public onFilter(value: Event): void {

  }

  addReport(id: any) {
    this.showBookAppointmentModal = true;
    const modalRef = this.modalService.open(LabReportComponent, { backdrop: 'static' });
    modalRef.componentInstance.appointmentId = id;
    modalRef.result.then((result: any) => {
      if (result) {

      }
    })
  }



  viewReport(appointmentId: any): void {
    const reportUrl = `http://localhost:8090/health/reports/getreport/${this.patientid}/${appointmentId}`;
// Sanitize the URL
    const safeUrl: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl(reportUrl);
    if (reportUrl) {
      this.pdfSrc = safeUrl;
      this.showModal = true;
    } else {
      console.error('Invalid report URL');
    }
  }


  getPrescription(patientid: any,appointmentid: any){
    this.patientService.getPrescription(this.patientid,appointmentid).subscribe((res: any) => {
      if (res) {
        // this.prescription = res;
        return res;
    }
  });
}


openPrescriptionModal(patientid: any,appointmentid: any): void {
  const modalRef = this.modalService.open(ViewprescriptionComponent, { backdrop: 'static' });
  modalRef.componentInstance.patientId = patientid;
  modalRef.componentInstance.appointmentId = appointmentid;
  modalRef.result.then((result: any) => {
    if (result) {

    }
  });
}

  closeModal(): void {
    this.showModal = false;
  }

}