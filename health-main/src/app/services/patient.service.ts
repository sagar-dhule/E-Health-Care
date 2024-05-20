import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

const NAV_URL = environment.authUrl;

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  
  

  private appointmentListSubject = new BehaviorSubject<void>(undefined);

  constructor(private _http: HttpClient) { }


  notifyAppointmentAdded() {
    this.appointmentListSubject.next();
  }

  get appointmentAdded$() {
    return this.appointmentListSubject.asObservable();
  }

  getAllUsers(): Observable<any> {
    return this._http.get<any>(`${NAV_URL}userlist`);
  }

  saveAppointment(appointment: Appointment): Observable<any> {
    return this._http.post<any>(`${NAV_URL}patient/bookNewAppointment`, appointment);
  }

  updateAppointment(appointment: any): Observable<any> {
    return this._http.put<any>(`${NAV_URL}patient/updateAppointment`, appointment);
  }

  getPrescriptionsByName(name: string): Observable<any> {
    return this._http.get<any>(`${NAV_URL}getprescriptionbyname/` + name);
  }

   getProfileDetails(email: any): Observable<any> {
    return this._http.get(`${NAV_URL}user/profileDetails/` + email);
  }

   UpdateUserProfile(user: any): Observable<any> {
    return this._http.put<any>(`${NAV_URL}updateuser`, user);
  }


   getAppointmentList(patientid: any): Observable<any> {
    return this._http.get<any>(environment.authUrl + "patient/getAppointmentsList/" + patientid);
  }


  getDoctorListBySpeciality(specialityId: any): Observable<any> {
    return this._http.get(environment.authUrl + "doctor/allDoctorsBySpeciality/" + specialityId);
  }


  saveReview(reviewData: any): Observable<any> {
    return this._http.post<any>(`${NAV_URL}patient/save/reviews`, reviewData);
  }

  getALlPatients() {
    return this._http.get<any>(`${NAV_URL}patient/allPatients`);
  }

  getDoctorPaymentAmount(patientId: any) {
    return this._http.get<any>(`${NAV_URL}payment/getByDoctorId/`,patientId);
  }

  MakePayment(payment: any)
    {
      return this._http.post<any>(`${NAV_URL}payment/save`, payment);
    }

    getPrescription(patientid: any,appointmentid: any){
      return this._http.get<any>(environment.authUrl+ "patient/getprescription/"+patientid+"/"+appointmentid);
    }

}
