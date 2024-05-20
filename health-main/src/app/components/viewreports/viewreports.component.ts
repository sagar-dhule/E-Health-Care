import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-viewreports',
  templateUrl: './viewreports.component.html',
  styleUrl: './viewreports.component.css'
})
export class ViewreportsComponent implements OnInit {

  @Input() reportIds: any;
  reportList!: any[];


  constructor(public activeModal: NgbActiveModal,
    private patientService:PatientService) { }



  ngOnInit(): void {
    if (this.reportIds != null) {
      this.reportList = this.reportIds.map((report: any) => {
        const reportid = report.id;
        const url = `http://localhost:8090/health/reports/getLabReport/${reportid}`;
        return { ...report, documentUrl: url };
      });
    }
  }

  close(): void {
    this.activeModal.dismiss();
  }

}

