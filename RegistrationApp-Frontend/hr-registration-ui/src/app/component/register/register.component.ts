import { ClientResponse } from './../../model/client-response.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {

  email: string = '';
  response: ClientResponse = new ClientResponse();

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  onSubmit() {
    this.userService.registerNewUser(this.email).subscribe(data => {
      this.response = data;
      console.log(this.response.message);
      console.log(this.response.user);
      alert(this.response.message);
    },
    err =>{
      this.response = err;
      console.log(err.error.message);
      console.log(err.error.user);
    }
    );
  }
}
