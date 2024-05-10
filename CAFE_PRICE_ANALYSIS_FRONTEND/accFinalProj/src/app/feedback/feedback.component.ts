import { Component } from '@angular/core';
import { FeedbackService } from './feedback.service';
import { feedbackDTO } from './dto/feedbackDTO';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  comments: any;
  feedbackBody: feedbackDTO;
  userId: number;
  analysis_rating: number = 0;
  dashboard_rating: number = 0;
  responseMessage: any;
  responseCode: any;

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {

  }

  rating(key: string, rating: number) {
    this.userId = Number(localStorage.getItem("userId"));

    if(key == 'D') {
      this.dashboard_rating = rating;
    } else if(key == 'A') {
      this.analysis_rating = rating;
    }
  }

  toggleDash(key: number) {
    if(key == 1) {
      $("#dash_1").addClass('active');
      $("#dash_2").removeClass('active');
      $("#dash_3").removeClass('active');
      $("#dash_4").removeClass('active');
      $("#dash_5").removeClass('active');
    } else if(key == 2) {
      $("#dash_1").removeClass('active');
      $("#dash_2").addClass('active');
      $("#dash_3").removeClass('active');
      $("#dash_4").removeClass('active');
      $("#dash_5").removeClass('active');
    } else if(key == 3) {
      $("#dash_1").removeClass('active');
      $("#dash_2").removeClass('active');
      $("#dash_3").addClass('active');
      $("#dash_4").removeClass('active');
      $("#dash_5").removeClass('active');
    } else if(key == 4) {
      $("#dash_1").removeClass('active');
      $("#dash_2").removeClass('active');
      $("#dash_3").removeClass('active');
      $("#dash_4").addClass('active');
      $("#dash_5").removeClass('active');
    } else if(key == 5) {
      $("#dash_1").removeClass('active');
      $("#dash_2").removeClass('active');
      $("#dash_3").removeClass('active');
      $("#dash_4").removeClass('active');
      $("#dash_5").addClass('active');
    }
  }

  toggleAnalysis(key: number) {
    if(key == 1) {
      $("#analysis_1").addClass('active');
      $("#analysis_2").removeClass('active');
      $("#analysis_3").removeClass('active');
      $("#analysis_4").removeClass('active');
      $("#analysis_5").removeClass('active');
    } else if(key == 2) {
      $("#analysis_1").removeClass('active');
      $("#analysis_2").addClass('active');
      $("#analysis_3").removeClass('active');
      $("#analysis_4").removeClass('active');
      $("#analysis_5").removeClass('active');
    } else if(key == 3) {
      $("#analysis_1").removeClass('active');
      $("#analysis_2").removeClass('active');
      $("#analysis_3").addClass('active');
      $("#analysis_4").removeClass('active');
      $("#analysis_5").removeClass('active');
    } else if(key == 4) {
      $("#analysis_1").removeClass('active');
      $("#analysis_2").removeClass('active');
      $("#analysis_3").removeClass('active');
      $("#analysis_4").addClass('active');
      $("#analysis_5").removeClass('active');
    } else if(key == 5) {
      $("#analysis_1").removeClass('active');
      $("#analysis_2").removeClass('active');
      $("#analysis_3").removeClass('active');
      $("#analysis_4").removeClass('active');
      $("#analysis_5").addClass('active');
    }
  }

  feedback() {
    if(!this.validate()) {
      Swal.fire("Error", "Please select a rating before proceeding.", "error");
    } else {
      this.feedbackBody = {
        "userid": this.userId,
        "dashboard_rating": this.dashboard_rating,
        "analysis_rating": this.analysis_rating,
        "comments": this.comments
      }
  
      this.feedbackService.feedback(this.feedbackBody).subscribe((data: any) => {
        this.responseMessage = data["responseMessage"];
        this.responseCode = data["responseCode"];
        
        if(this.responseCode == 1000) {
          Swal.fire("Success", this.responseMessage, "success");
          this.clearForm();
        } else {
          Swal.fire("Error", this.responseMessage, "error");
          this.clearForm();
        }
      })
    }
  }

  clearForm() {
    this.dashboard_rating = 0;
    this.analysis_rating = 0;
    this.comments = '';

    $("#dash_1").removeClass('active');
    $("#dash_2").removeClass('active');
    $("#dash_3").removeClass('active');
    $("#dash_4").removeClass('active');
    $("#dash_5").removeClass('active');

    $("#analysis_1").removeClass('active');
    $("#analysis_2").removeClass('active');
    $("#analysis_3").removeClass('active');
    $("#analysis_4").removeClass('active');
    $("#analysis_5").removeClass('active');
  }

  validate(): boolean {
    if(this.dashboard_rating == 0 || this.analysis_rating == 0) {
      return false;
    }
    
    return true;
  }
}
