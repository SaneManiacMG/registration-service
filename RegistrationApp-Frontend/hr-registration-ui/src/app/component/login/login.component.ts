import { ClientResponse } from './../../model/client-response.model';
import { UserService } from 'src/app/service/user.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  response: ClientResponse = new ClientResponse();
  loginRequest: Login = new Login();

  constructor(private userService: UserService) { }

  onSubmit() {
    this.userService.loginUser(this.loginRequest).subscribe(data => {
        this.response = data;
        console.log(this.response.message);
        console.log(this.response.user);
        alert(this.response.message);
      },
      err => {
        console.log(err.error.message);
        console.log(err.error.user);
        alert(err.error.message);
      })

  }
}
