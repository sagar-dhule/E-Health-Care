
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


const NAV_URL = environment.authUrl;

@Injectable({
  providedIn: 'root'
})
export class LabreportsService {

  constructor(private _http:HttpClient) { }

  SaveLabReport(labData: any){
    
      return this._http.post<any>(`${NAV_URL}reports/upload`,labData);
  
  }

  geReports(patientid: any,appointmentid: any):Observable<Blob>{
    
    return this._http.get<Blob>(`${NAV_URL}reports/getreport/`+patientid+"/"+appointmentid,{ responseType: 'blob' as 'json' });

}

geLabReports(reportid: any):Observable<Blob>{
    
  return this._http.get<Blob>(`${NAV_URL}reports/getLabReport/`+reportid,{ responseType: 'blob' as 'json' });

}
}
