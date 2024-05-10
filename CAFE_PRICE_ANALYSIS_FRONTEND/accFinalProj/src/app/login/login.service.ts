import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginURL = '/rest/api/login';
  private registerURL = '/rest/api/register';
  private resetPasswordUrl = '/rest/api/resetPassword';

  constructor(private http: HttpClient) { }

  login(loginBody: any) {
    return this.http.post<any[]>(this.loginURL, loginBody);
  }

  register(registBody: any) {
    return this.http.post<any[]>(this.registerURL, registBody);
  }

  resetPassword(resetBody: any) {
    return this.http.post<any[]>(this.resetPasswordUrl, resetBody);
  }
}
