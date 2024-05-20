import { Component, Input } from '@angular/core';
import { LabreportsService } from '../../services/labreports.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-labreport',
    templateUrl: './labreport.component.html',
    styleUrl: './labreport.component.css'
})
export class LabReportComponent {


    @Input() appointmentId: any;
    user: any;
    patientid: any;
    reportName: string = '';
    reportFile: File | null = null;
    uploadSuccessMessage: string | null = null;
    uploadErrorMessage: string | null = null;

    constructor(private labService: LabreportsService,
        public activeModal: NgbActiveModal) { }

    ngOnInit(): void {

        this.user = JSON.parse(localStorage.getItem('auth-user') || '{}');

        this.patientid = this.user?.id;

    }

    showCard: boolean = false;

    toggleCard() {
        this.showCard = !this.showCard;
    }

    onSubmit(): void {
        if (!this.reportFile) {
            this.uploadErrorMessage = 'Please select a file to upload.';
            return;
        }

        const formData = new FormData();
        formData.append('patientid', this.patientid);
        formData.append('appointmentid', this.appointmentId);
        formData.append('reportname', this.reportName);
        formData.append('file', this.reportFile);

        this.labService.SaveLabReport(formData).subscribe(
            (response: any) => {
                if (response) {
                    this.uploadSuccessMessage = response.message;
                    setTimeout(() => {
                        this.dismiss();
                    }, 2000);
                }
            },
            () => {
                this.uploadErrorMessage = 'Failed to upload report. Please try again.';
            }
        );
    }

    onFileSelected(event: any): void {
        this.reportFile = event.target.files[0];
    }

    dismiss(): void {
        this.activeModal.dismiss();
    }
}
