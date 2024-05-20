import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Userprofile } from '../../models/userprofile';
import { TokenStorageService } from '../../services/token-storage.service';
import { PatientService } from '../../services/patient.service';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ViewreportsComponent } from '../viewreports/viewreports.component';
import { LabreportsService } from '../../services/labreports.service';
import { ToastrService } from 'ngx-toastr';
import { CustomDatePipe } from '../../custom/custom-date.pipe';



@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  paymentDetails!: any[];
  user: any;
  patientid: any;

  selected: any;

  constructor(
    private patientService: PatientService,
    public activeModal: NgbActiveModal,
    private reportService: LabreportsService,
    private customDatePipe: CustomDatePipe,
    public modalService: NgbModal,
  ) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('auth-user') || '{}');

    this.patientid = this.user?.id;

    this.getDoctorPaymentAmount(this.patientid);
  }

  getDoctorPaymentAmount(patientId: any) {

    this.patientService.getAppointmentList(patientId).subscribe((res: any) => {
      if (res) {
        this.paymentDetails = res;

        this.paymentDetails?.forEach((appt: any) => {
          const transformDate = this.customDatePipe.transform(appt.date);
          appt.date = transformDate;
        })
      }

    })
  }


  pay(payment: any): void {


    let paymentObj = {
      patientid: payment.patientid,
      doctorid: payment.doctorid,
      appointmentid: payment.id,
      amount: payment.amount
    }
    this.patientService.MakePayment(paymentObj).subscribe((res) => {
      if (res) {
        this.getDoctorPaymentAmount(paymentObj.patientid);
      }
    })

  }


  close(): void {
    this.activeModal.dismiss();
    this.patientService.notifyAppointmentAdded();
  }

}

