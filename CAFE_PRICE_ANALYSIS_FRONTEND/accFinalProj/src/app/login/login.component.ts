import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { userDTO } from '../registration/dto/userDTO';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  empId: string = '';
  password: string = '';
  loginBody: userDTO;
  registrationBody: userDTO;
  responseMessage: any;
  responseCode: any;
  showHidePass: boolean = false;
  passwordFlag: any;
  forgotFlag: boolean = false;
  rePassword: string = '';
  resetBody: userDTO;

  constructor(private loginService: LoginService,
    private router: Router) {
      this.passwordFlag = 'password';
    }

  validation(): boolean {
    if(this.empId.length == 9 && this.password.length >= 8) {
      return true;
    }

    return false;
  }

  login() {
    if(!this.validation()) {
      Swal.fire("Warning", "Invalid Credentials!", "warning");
    } else {
      this.loginBody = {
        "empId": this.empId,
        "password": this.password
      }
  
      this.loginService.login(this.loginBody).subscribe((data: any) => {
        this.responseMessage = data["responseMessage"];
        this.responseCode = data["responseCode"];
        if(this.responseCode == 1000) {
          this.router.navigate(['dashboard', this.empId]);
          localStorage.setItem("userId", this.empId);
        } else {
          Swal.fire("Warning", this.responseMessage, "warning");
        }
        
        //this.loginResponse = data;
      });
    }   
  }

  register() {
    if(!this.validation()) {
      Swal.fire("Warning", "Invalid Credentials!", "warning");
    } else {
      this.registrationBody = {
        "empId": this.empId,
        "password": this.password
      }
  
      this.loginService.register(this.registrationBody).subscribe((data: any) => {
        this.responseMessage = data["responseMessage"];
        this.responseCode = data["responseCode"];
  
        if(this.responseCode == 1000) {
          Swal.fire("Success", this.responseMessage, "success");
        } else if(this.responseCode == 2000) {
          Swal.fire("Info", this.responseMessage, "info");
        } else {
          Swal.fire("Success", this.responseMessage, "success");
        }
      });
    }   
  }

  showHide() {
    if(this.passwordFlag === 'password') {
      this.passwordFlag = 'text';
      this.showHidePass = true;
    } else {
      this.passwordFlag = 'password';
      this.showHidePass = false;
    }
  }

  forgotPassword() {
    this.forgotFlag = true;
  }

  backToLogin() {
    this.forgotFlag = false;
    this.router.navigate(['login']);
  }

  resetValidation(): boolean {
    if(this.password == this.rePassword) {
      return true;
    }

    return false;
  }

  resetPassword() {
    if(!this.validation()) {
      Swal.fire("Warning", "Invalid Credentials!", "warning");
    } else if(!this.resetValidation()) {
      Swal.fire('Warning', 'Passwords do not match. Please try again.', 'warning');
    } else {
      this.resetBody = {
        "empId": this.empId,
        "password": this.password
      }
      this.loginService.resetPassword(this.resetBody).subscribe((data: any) => {
        this.responseMessage = data["responseMessage"];
        this.responseCode = data["responseCode"];

        if(this.responseCode == 1000) {
          Swal.fire('Success', this.responseMessage, 'success');
        } else if(this.responseCode == 2000) {
          Swal.fire('Error', this.responseMessage, 'error');
        } else {
          Swal.fire('Error', this.responseMessage, 'error');
        }
      })
    }
  }
}
