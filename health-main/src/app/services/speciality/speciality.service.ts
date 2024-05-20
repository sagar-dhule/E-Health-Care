import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
const httpOptions={
  Headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class SpecialityService {

  constructor(private http: HttpClient) { }

  getAllDoctors(): Observable<any> {
    return this.http.get(environment.authUrl + "doctor/allDoctors");
  }
  getAllSpeciality(): Observable<any> {
    return this.http.get(environment.authUrl + "doctor/allSpeciality");
  }
  
}
