import { ClientResponse } from './../../model/client-response.model';
import { UserService } from 'src/app/service/user.service';
import { Component } from '@angular/core';
import { Login } from 'src/app/model/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService) {

  }

  response: ClientResponse = new ClientResponse();

  request: Login = new Login();

  onSubmit() {
    this.userService.loginUser(this.request)
      .subscribe(data => {
        this.response = data;
        console.log(this.response.message);
        alert(this.response.message);
      },
      err => {
        console.log(err.error.message);
        alert(err.error.message);
      })

  }
}
