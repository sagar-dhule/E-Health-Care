import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrl: './add-doctor.component.css'
})
export class AddDoctorComponent implements OnInit {
  addDoctorForm!: FormGroup; 
  doctorAdd: boolean = false;

  constructor() { }

  ngOnInit(): void {
    // Initialize the FormGroup instance with form controls
    this.addDoctorForm = new FormGroup({
      doctorname: new FormControl('', Validators.required),
      specialty: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phonenumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
      qualification: new FormControl('', Validators.required),
      image: new FormControl('', Validators.required)
    });
  }

  onSubmit(formData: any) {
    
    console.log(formData);
    this.doctorAdd = true;
  }

  onFileSelected(event: any) {
    // Handle file selection
    console.log(event.target.files[0]);
  }
}