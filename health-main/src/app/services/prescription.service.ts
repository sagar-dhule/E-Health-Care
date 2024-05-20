import { Injectable } from '@angular/core';
import { Prescription } from '../models/prescription';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

const NAV_URL = environment.authUrl;

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {


  constructor(private _http:HttpClient) { }


  getPrescriptions(patientid : any,appointmentid: any) : Observable<any>
  {
    return this._http.get<any>(`${NAV_URL}prescription/getprescription/${patientid}/${appointmentid}`);
  }


  savePrescriptions(prescriptionReq: any): Observable<any> {
    return this._http.post<any>(`${NAV_URL}prescription/save`, prescriptionReq);
  }
}