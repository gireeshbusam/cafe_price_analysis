import { Component } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { getBsVer, IBsVersion } from 'ngx-bootstrap/utils';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  userId: number;

  constructor(private router: Router) {
    
  }

  get _getBsVer(): IBsVersion {
    return getBsVer();
  }

  ngOnInit(): void {
    //this.toggleTab('D');
    $("#dashboard").addClass('active');
    $("#price").removeClass('active');
    $("#reports").removeClass('active');
    $("#feedback").removeClass('active');
  }

  toggleTab(key: string) {
    
    if(key == 'D') {
      $("#dashboard").addClass('active');
      $("#price").removeClass('active');
      $("#reports").removeClass('active');
      $("#feedback").removeClass('active');
      this.userId = Number(localStorage.getItem("userId"));
      this.router.navigate(['dashboard', this.userId]);
    } else if(key == 'P') {
      $("#dashboard").removeClass('active');
      $("#price").addClass('active');
      $("#reports").removeClass('active');
      $("#feedback").removeClass('active');
      this.router.navigate(['analysis']);
    } else if(key == 'F') {
      $("#dashboard").removeClass('active');
      $("#price").removeClass('active');
      $("#reports").removeClass('active');
      $("#feedback").addClass('active');
      this.router.navigate(['feedback']);
    } 
    // else if(key == 'R') {
    //   $("#dashboard").removeClass('active');
    //   $("#price").removeClass('active');
    //   $("#reports").addClass('active');
    //   $("#feedback").removeClass('active');
    //   this.router.navigate(['reports']);
    // } 
    
  }

  logout() {
    localStorage.removeItem("userId");
    this.router.navigate(['login']);
  }
}
