import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../services/doctor.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CustomDatePipe } from '../../custom/custom-date.pipe';

@Component({
  selector: 'app-bookingdetails',
  templateUrl: './bookingdetails.component.html',
  styleUrl: './bookingdetails.component.css'
})
export class BookingdetailsComponent implements OnInit{


  appointments!: any[];

  constructor(public modalService: NgbModal,
    private doctorService: DoctorService,
    private customDatePipe: CustomDatePipe) { }

  ngOnInit(): void {

    this.getAllAppointments();

  }

  getAllAppointments(): void {
    this.doctorService.getAllAppointments().subscribe((res: any) => {
      if (res) {
        this.appointments = res;

        this.appointments?.forEach((appt: any) => {
          const transformDate = this.customDatePipe.transform(appt.startdate);
          appt.startdate = transformDate;
        })
      }

    })
}
}
