import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';


const httpOptions={
  Headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }
  login(obj: any): Observable<any>{
    return this.http.post(environment.authUrl + 'auth/signin', obj );
  }

  // register(obj: any,): Observable<any>{
  //   return this.http.post(environment.authUrl + 'auth/signup', obj);
  // }
  register(formData: any): Observable<any> {

    return this.http.post(environment.authUrl + 'auth/signup', formData);
}

  forgotPassword(email: string) {
    return this.http.post(environment.authUrl + 'forgot', { email });
  }

  resetPassword( obj: any) {
    return this.http.post(environment.authUrl + 'reset', obj);
}
 logout() {
  return this.http.get(environment.authUrl + 'signout');
}
}
