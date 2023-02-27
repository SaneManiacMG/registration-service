import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service/user.service';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  email: string = '';

  constructor(private userService: UserService,
    private router: Router) {}

  ngOnInit(): void {

  }


  onSubmit() {
    console.log("Email submitted: " + this.email);
    this.userService.registerNewUser(this.email).subscribe((response: any) =>
     {
      console.log(response);
      alert(response);
     });
  }
}
