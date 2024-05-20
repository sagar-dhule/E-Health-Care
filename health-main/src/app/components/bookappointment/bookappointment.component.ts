import { Component } from '@angular/core';
import { Appointment } from '../../models/appointment';
import { Observable } from 'rxjs';
import { Slots } from '../../models/slots';
import { PatientService } from '../../services/patient.service';
import { DoctorService } from '../../services/doctor.service';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { SpecialityService } from '../../services/speciality/speciality.service';

@Component({
  selector: 'app-bookappointment',
  templateUrl: './bookappointment.component.html',
  styleUrl: './bookappointment.component.css'
})
export class BookappointmentComponent {

  bookappointmentform!: FormGroup;

  currRole = '';
  loggedUser: any;
  message = '';
  showModal = true;
  appointment: any;
  slots: Observable<Slots[]> | undefined;
  doctors: any;
  specializations: any;
  doctId: any;
  doctName: any;

  constructor(private formBuilder: FormBuilder,
    private specialityService: SpecialityService,
    private sanitizer: DomSanitizer,
    private _router: Router, private patientService: PatientService,
    public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.bookappointmentform = this.formBuilder.group({
      patientname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      doctorname: ['', Validators.required],
      specialization: ['', Validators.required],
      startdate: ['', Validators.required],
      slot: ['', Validators.required],
      age: ['', Validators.required],
      gender: ['', Validators.required],
      problem: ['', Validators.required]
    });
    this.bookappointmentform.get('specialization')?.setValue('Select speciality');

    this.getAllSpeciality();

    this.bookappointmentform.get('specialization')?.valueChanges.subscribe((data: any) => {
      if (data) {

        //console.log("this.bookappointmentform.get('specialization')=>",data);

        this.specializations.forEach((item: any) => {
          if (item.id == data) {
            const specialityId = item?.id;

            this.bookappointmentform.get('doctorname')?.setValue('Select doctor');
            this.getDoctorListBySpeciality(specialityId);
          }
        })

      }
    });


    this.bookappointmentform.get('doctorname')?.valueChanges.subscribe((data: any) => {
      if (data) {

        console.log("this.bookappointmentform.get('doctorname')=>", data);

        this.doctors?.forEach((item: any) => {
          if (item.doctorid == data) {
            this.doctId = item?.doctorid;
            this.doctName = item?.doctorname;

          }
        });
      }
    });


    this.loggedUser = JSON.parse(localStorage.getItem('auth-user') || '{}');

    this.bookappointmentform.patchValue({
      patientname: this.loggedUser.firstname + ' ' + this.loggedUser.lastname,
      email: this.loggedUser.email
    })
  }


  

  bookAppointment() {

    this.appointment = {

      patientname: this.bookappointmentform.value.patientname,
      patientid: this.loggedUser.id,
      email: this.bookappointmentform.value.email,
      doctorname: this.doctName,
      doctorid: this.doctId,
      specialization: this.bookappointmentform.value.specialization,
      startdate: this.bookappointmentform.value.startdate,
      age: this.bookappointmentform.value.age,
      gender: this.bookappointmentform.value.gender,
      problem: this.bookappointmentform.value.problem,
      slot: this.bookappointmentform.value.slot,
      appointmentstatus: "pending"

    }
  
    this.patientService.saveAppointment(this.appointment).subscribe(
      (data:any) => {
        this.dismiss();
        this._router.navigate(['/patient-dashboard']);
        this.patientService.notifyAppointmentAdded();
      },
      (error: any) => {
        console.log("Process failed");
        this.message = "There is a problem in booking your appointment, please check slot availability and try again!";
        console.log(error.error);
      }
    );
  }

  getDoctorListBySpeciality(specialityid: any) {

    this.patientService.getDoctorListBySpeciality(specialityid).subscribe((data: any) => {
      if (data) {
        this.doctors = data;
      }

    })
  }

  getAllSpeciality() {
    this.specialityService.getAllSpeciality().subscribe((data: any) => {
      if (data) {
        this.specializations = data;
      }

    })
  }

  dismiss(): void {
    this.activeModal.dismiss();
  }
}