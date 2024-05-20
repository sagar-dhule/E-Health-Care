import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { LabreportsService } from '../../services/labreports.service';
import { ToastrService } from 'ngx-toastr';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ViewreportsComponent } from '../viewreports/viewreports.component';

@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrl: './patientlist.component.css'
})
export class PatientlistComponent implements OnInit {

 patients!: any[];
  
  selected: any;

  constructor(
    private patientService:PatientService,
    private reportService: LabreportsService,
    public modalService: NgbModal,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.getDoctorList();
  }

  getDoctorList() {
      this.patientService.getALlPatients().subscribe((res: any) => {
        if (res) {
          this.patients = res;

          console.log( this.patients);

        }
    
  });
}

showReportDetails(reportIds: any): void{
    const modalRef = this.modalService.open(ViewreportsComponent, { backdrop: 'static' });
    modalRef.componentInstance.reportIds = reportIds;
    modalRef.result.then((result: any) => {
      
       
    });

  }


}
