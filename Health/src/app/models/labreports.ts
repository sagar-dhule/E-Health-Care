export class LabReport {
    id: number;
    patientId: string;
    patientName: string;
    reportDate: Date;
    testType: string;
    results: string;
    remarks: string;
  
    constructor(
      id: number,
      patientId: string,
      patientName: string,
      reportDate: Date,
      testType: string,
      results: string,
      remarks: string
    ) {
      this.id = id;
      this.patientId = patientId;
      this.patientName = patientName;
      this.reportDate = reportDate;
      this.testType = testType;
      this.results = results;
      this.remarks = remarks;
    }
  }