import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { PatientService } from '../../services/patient.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  profileDetails: Observable<User[]> | undefined;
  user: User = new User();
  msg = ' ';
  currRole = '';
  showProfileCard = true;
  showProfileForm = false;
  showModal = true;

  Updateform!: FormGroup;
  temp: boolean = false;
  loggedUser: any; // Assuming you have a logged-in user

  constructor(private formBuilder: FormBuilder, private patientService: PatientService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.Updateform = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$')]],
      username: ['', Validators.required],
      gender: ['', Validators.required],
      age: ['', Validators.required],
      mobile: ['', Validators.required],
      password: ['', Validators.required]
    });
  }



  ngOnInit(): void {
    const data = localStorage.getItem('auth-user');
    if (data) {

      this.loggedUser = JSON.parse(data);
      this.Updateform.patchValue({
        username:this.loggedUser.firstname +" "+this.loggedUser.lastname,

      })
    } else {
      console.error('No user data found in localStorage');
    }



    this.currRole = this.loggedUser.type || '';

    this.getProfileDetails(this.loggedUser);
  }

  editProfile() {
    this.showProfileCard = false;
    this.showProfileForm = true;
  }

  getProfileDetails(loggedUser: string) {
    this.profileDetails = this.patientService.getProfileDetails(loggedUser);
    console.log(this.profileDetails);
  }

  updateUserProfile() {
    this.patientService.UpdateUserProfile(this.user).subscribe(
      data => {
        console.log("UserProfile Updated succesfully");
        this.msg = "Profile Updated Successfully !!!";
        this.temp = true;
        this.showProfileCard = true;
        this.showProfileForm = false;
        setTimeout(() => {
          this.router.navigate(['/userdashboard']);
        }, 6000);
      },
      error => {
        console.log("Profile Updation Failed");
        console.log(error.error);
        this.msg = "Profile Updation Failed !!!";
      }
    )
  }
}