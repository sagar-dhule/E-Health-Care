import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PrescriptionService } from '../../services/prescription.service';

@Component({
  selector: 'app-viewprescription',
  templateUrl: './viewprescription.component.html',
  styleUrl: './viewprescription.component.css'
})
export class ViewprescriptionComponent {

  @Input() appointmentId: any;
  @Input() patientId: any;

  prescriptionText: string = '';

  hideButton:boolean=false;

  constructor(
    public activeModal: NgbActiveModal,
    private precriptionService: PrescriptionService
  ) { }

  ngOnInit(): void {
    if (this.appointmentId && this.patientId) {
     
      this.fetchPrescriptionData(this.appointmentId ,this.patientId);
    }
    
  }

  fetchPrescriptionData(appointmentid: any,patientid: any): void {
    
    this.precriptionService.getPrescriptions(patientid,appointmentid)
      .subscribe(
        (response: any) => {
          
          this.prescriptionText = response?.prescription;
        },
        (error:any) => {
          console.error('Error fetching prescription data:', error);
          // Handle error here
        }
      );
  }


  close(): void {
    this.activeModal.dismiss('Cross click');
  }
}
