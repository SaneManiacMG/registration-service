import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { Authentication } from 'src/app/model/authentication.model';
import { ClientResponse } from './../../model/client-response.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  users: User[] = [];
  authenticationRequest: Authentication = new Authentication();
  response: ClientResponse = new ClientResponse();

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getUsersList();
  }

  getUsersList() {
    this.userService.getUsersList().subscribe((data) => {
      this.users = data;
    });
  }

  approveRequest(email: string) {
    this.authenticationRequest.email = email;
    this.authenticationRequest.activate = true;
    console.log(this.authenticationRequest);
    this.userService.authentication(this.authenticationRequest).subscribe(
      (data) => {
        this.response = data;
        console.log(this.response.message);
        alert(this.response.message);
      },
      (err) => {
        console.log(err.error.message);
        alert(err.error.message);
      }
    );
  }

  rejectRequest(email: string) {
    this.authenticationRequest.email = email;
    this.authenticationRequest.activate = false;
    console.log(this.authenticationRequest);
    this.userService.authentication(this.authenticationRequest).subscribe(
      (data) => {
        this.response = data;
        console.log(this.response.message);
        alert(this.response.message);
      },
      (err) => {
        console.log(err.error.message);
        alert(err.error.message);
      }
    );
  }
}
