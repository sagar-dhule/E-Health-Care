import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Doctor } from '../models/doctor';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Slots } from '../models/slots';

const NAV_URL = environment.authUrl;

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  


  user = new User();
  doctor = new Doctor();

  constructor(private _http : HttpClient) { }

  addDoctorFromRemote(doctor : any) : Observable<any>
  {
      return this._http.post<any>(`${NAV_URL}/addDoctor`,doctor)
  }


  getDoctorSpecilizationList(): Observable<any>
  {
    return this._http.get<any>(`${NAV_URL}/speclizationlist`);
  }

  getDoctorList() : Observable<any>
  {
    return this._http.get<any>(`${NAV_URL}doctor/allDoctors`);
  }

  
  getAppointmentByDoctorID(doctorid: any): Observable<any>
  {
    return this._http.get<any>(`${NAV_URL}doctor/getAllAppointments/`+doctorid);
  }


  getAllAppointments() {
    return this._http.get<any>(`${NAV_URL}doctor/getAllAppointments`);
  }

}
