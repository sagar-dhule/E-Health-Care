export class Prescription {
    id: number;
    patientName: string;
    datePrescribed: Date;
    medications: string;
    dosage: string;
    instructions: string;
    doctor: string;
  
    constructor(
      id: number,
      patientName: string,
      datePrescribed: Date,
      medications: string,
      dosage: string,
      instructions: string,
      doctor: string
    ) {
      this.id = id;
      this.patientName = patientName;
      this.datePrescribed = datePrescribed;
      this.medications = medications;
      this.dosage = dosage;
      this.instructions = instructions;
      this.doctor = doctor;
    }
  }
  