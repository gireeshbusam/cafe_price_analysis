import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private feedbackUrl = '/rest/api/feedback';

  constructor(private http: HttpClient) { }

  feedback(feedbackBody: any) {
    return this.http.post<any[]>(this.feedbackUrl, feedbackBody);
  }
}
