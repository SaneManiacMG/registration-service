import { Component } from '@angular/core';
import { Users } from 'src/app/model/users.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(
      user: Users
    ) {}

    registerUser() { alert("alert popup") }
}
