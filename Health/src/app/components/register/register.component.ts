import { ChangeDetectorRef, Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { SpecialityService } from '../../services/speciality/speciality.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  showModal = true;
  specializations: any;
  registrationForm!: FormGroup;
  isSignUpFailed: boolean = false;
  errorMessage: string = '';
  isSuccessful: boolean = false;
  @ViewChild('imageInput') imageInput!: ElementRef;
    selectedFile: File | null = null;

    formData: any;

  constructor(private fb: FormBuilder, 
    public activeModal: NgbActiveModal,
    private authService: AuthService,
    private specialityService: SpecialityService,
    private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      gender: ['', Validators.required],
      age: ['', Validators.required],
      mobile: ['', Validators.required],
      type: ['', Validators.required],
      specialization: [''],
      experience: [''],
      lienceno:[''],
      address: [''],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmpassword: ['', Validators.required]
    });

    this.getAllSpeciality();

    // Adding conditional validators
    this.registrationForm.get('type')!.valueChanges.subscribe(value => {
      if (value === 'doctor') {
        this.registrationForm.get('specialization')!.setValidators([Validators.required]);
        this.registrationForm.get('experience')!.setValidators([Validators.required]);
        this.registrationForm.get('lienceno')!.setValidators([Validators.required]);
      } else {
        this.registrationForm.get('specialization')!.clearValidators();
        this.registrationForm.get('specialization')!.reset();
        this.registrationForm.get('experience')!.clearValidators();
        this.registrationForm.get('experience')!.reset();
        this.registrationForm.get('lienceno')!.clearValidators();
        this.registrationForm.get('lienceno')!.reset();
      }
      this.registrationForm.updateValueAndValidity();
      this.cdr.detectChanges(); 
    });
  
}

  onSubmit(): void {
    if (this.registrationForm.valid) {
      const signupReqObj: any = {}; // Create an empty object

      // Set form values one by one
      signupReqObj.firstname = this.registrationForm.value.firstname;
      signupReqObj.lastname = this.registrationForm.value.lastname;
      signupReqObj.gender = this.registrationForm.value.gender;
      signupReqObj.age = this.registrationForm.value.age;
      signupReqObj.type= this.registrationForm.value.type;
      signupReqObj.specialization = this.registrationForm.value.specialization;
      signupReqObj.experience = this.registrationForm.value.experience;
      signupReqObj.lienceno = this.registrationForm.value.lienceno;
      signupReqObj.address = this.registrationForm.value.address;
      signupReqObj.email = this.registrationForm.value.email;
      signupReqObj.password = this.registrationForm.value.password;
      signupReqObj.confirmpassword = this.registrationForm.value.confirmpassword;
      


      if(signupReqObj){
        this.authService.register(signupReqObj).subscribe(
          response => {
            console.log(response);
            if(response){
              this.isSuccessful = true;
              this.showModal = false;
              if (this.activeModal) {
                this.activeModal.dismiss(); 
              } else {
                console.error("activeModal is not defined");
              }
            }
           
          },
          error => {
            console.log(error);
            this.errorMessage = 'Enter Correct Data';
            this.isSignUpFailed = true;
          }
        );
      }
      
    
    }
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