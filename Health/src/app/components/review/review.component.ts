import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PatientService } from '../../services/patient.service';
import { SpecialityService } from '../../services/speciality/speciality.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {


  reviewform!: FormGroup;
  doctors: any;
  specializations: any;
  doctId: any;
  doctName: any;
  currRole = '';
  loggedUser: any;
  message = '';
  showModal = true;
  review: any;
  specialityId: any;

  constructor(private formBuilder: FormBuilder,
    private specialityService: SpecialityService,
    private _router: Router,
    private patientService: PatientService,
    public activeModal: NgbActiveModal) { }

  ngOnInit(): void {

    this.loggedUser = JSON.parse(localStorage.getItem('auth-user') || '{}');


    this.reviewform = this.formBuilder.group({
      doctorname: ['', Validators.required],
      specialization: ['', Validators.required],
      reviewtext: ['']
    });
    this.reviewform.get('specialization')?.setValue('Select speciality');

    this.getAllSpeciality();

    this.reviewform.get('specialization')?.valueChanges.subscribe((data: any) => {
      if (data) {

        //console.log("this.reviewform.get('specialization')=>",data);

        this.specializations.forEach((item: any) => {
          if (item.id == data) {
             this.specialityId = item?.id;

            this.reviewform.get('doctorname')?.setValue('Select doctor');
            this.getDoctorListBySpeciality(this.specialityId);
          }
        })

      }
    });


    this.reviewform.get('doctorname')?.valueChanges.subscribe((data: any) => {
      if (data) {

        console.log("this.reviewform.get('doctorname')=>", data);

        this.doctors?.forEach((item: any) => {
          if (item.doctorid == data) {
            this.doctId = item?.doctorid;
            this.doctName = item?.doctorname;

          }
        });
      }
    });
  }

  saveReview() {

    this.review = {
      patientid: this.loggedUser.id,
      doctorid: this.doctId,
      specialityid:this.specialityId,
      reviewtext: this.reviewform.value.reviewtext,

    }
  
    this.patientService.saveReview(this.review).subscribe(
      (data: any) => {
        this.dismiss();
        this._router.navigate(['/patient-dashboard']);
      },
     ( error: any) => {
        console.log("Process failed");
        this.message = "There is a problem in saving review ,please try again!";
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

    console.log("Hiiiiiiiii");
    
    this.activeModal.dismiss();
  }
}
