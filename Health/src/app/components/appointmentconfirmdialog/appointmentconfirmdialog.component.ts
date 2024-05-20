import { Component, Input, input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-appointmentconfirmdialog',
  standalone: true,
  imports: [],
  templateUrl: './appointmentconfirmdialog.component.html',
  styleUrl: './appointmentconfirmdialog.component.css'
})
export class AppointmentconfirmdialogComponent {

  @Input() appointment: any;


  constructor(public activeModal: NgbActiveModal,
    private patientService:PatientService) { }

  confirmAppointment(): void {

    this.activeModal.close('confirmed');
  }

  close(): void {
    // Close the modal without confirming
    this.activeModal.dismiss();
  }

}
