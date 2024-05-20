import { Component, Input } from '@angular/core';
import { Prescription } from '../../models/prescription';
import { PrescriptionService } from '../../services/prescription.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-prescriptions',
  templateUrl: './prescriptions.component.html',
  styleUrl: './prescriptions.component.css'
})
export class PrescriptionsComponent {
  @Input() appointmentId: any;
  @Input() patientid: any;

  @Input() prescription: any;

  prescriptionText: string = '';

  hideButton:boolean=false;

  constructor(
    public activeModal: NgbActiveModal,
    private precriptionService: PrescriptionService
  ) { }

  ngOnInit(): void {
    if (this.appointmentId && this.patientid) {
     
      this.fetchPrescriptionData();
    }
    
  }

  fetchPrescriptionData(): void {
    
    this.precriptionService.getPrescriptions(this.patientid,this.appointmentId)
      .subscribe(
        (response: any) => {
          // If prescription data is available, set it to prescriptionText
          this.prescriptionText = response?.prescription;
        },
        (error:any) => {
          console.error('Error fetching prescription data:', error);
          // Handle error here
        }
      );
  }

  savePrescription(): void {

    let obj={
      appointmentid:this.appointmentId,
      patientid:this.patientid,
      prescription:this.prescriptionText,
    }
    this.precriptionService.savePrescriptions(obj)
      .subscribe(
        (response: any) => {
          
        },
        (error:any) => {
          console.error('Error fetching prescription data:', error);
          // Handle error here
        }
      );
      this.activeModal.close('Prescription saved');
    
  }

  close(): void {
    this.activeModal.dismiss('Cross click');
  }
}