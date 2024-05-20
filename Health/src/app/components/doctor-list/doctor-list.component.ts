import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { DoctorService } from '../../services/doctor.service';



@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DocotorListComponent implements OnInit {


  id!: string;
  public doctors!: any[];
  
  selected: any;

  constructor(
    private doctorService:DoctorService,
  ) { }

  ngOnInit(): void {
   // this.initForm();
    this.getDoctorList();
  }

  getDoctorList() {
      this.doctorService.getDoctorList().subscribe((res: any) => {
        if (res) {
          this.doctors = res;

          console.log( this.doctors);


          this.doctors.forEach((doctor)=>{
            var date = new Date(doctor.createdOn);

            // Extract date components
            var year = date.getFullYear();
            var month = ("0" + (date.getMonth() + 1)).slice(-2);
            var day = ("0" + date.getDate()).slice(-2);
            var hours = ("0" + date.getHours()).slice(-2);
            var minutes = ("0" + date.getMinutes()).slice(-2);
            var seconds = ("0" + date.getSeconds()).slice(-2);

            
            var hoursNum = parseInt(hours);
            var amOrPm = hoursNum >= 12 ? "PM" : "AM";

  
            hours = (hoursNum % 12 || 12).toString();
            var formattedDate = year + "-" + month + "-" + day + " " + hours + ":" + minutes + " "+ amOrPm;

            doctor.createdOn=formattedDate;
          });
         

          
  
          // this.doctors?.forEach((appt: any) => {
          //   const transformDate = this.customDatePipe.transform(appt.date);
          //   appt.date = transformDate;
          // })
        }
  
      })
    
  }


}