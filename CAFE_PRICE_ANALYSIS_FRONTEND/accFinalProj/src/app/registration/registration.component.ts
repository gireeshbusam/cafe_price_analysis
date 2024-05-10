import { Component } from '@angular/core';
import { userDTO } from './dto/userDTO';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  empId: string;
  password: string;
  registrationBody: userDTO;

  constructor(private registrationService: RegistrationService) {}

  register() {
    this.registrationBody = {
      "empId": this.empId,
      "password": this.password
    }

    this.registrationService.register(this.registrationBody).subscribe((data) => {
      console.log(data);
    });
  }
}
