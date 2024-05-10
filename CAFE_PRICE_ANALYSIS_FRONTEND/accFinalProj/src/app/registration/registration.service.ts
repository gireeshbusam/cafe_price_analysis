import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  registerURL = '/rest/api/register';

  constructor(private http: HttpClient) { }

  register(registBody: any) {
    return this.http.post<any[]>(this.registerURL, registBody);
  }
}
