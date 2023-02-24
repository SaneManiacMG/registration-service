import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users : User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit() : void {
    this.getUsersList();
  }

  private getUsersList() {
    this.userService.getUsersList().subscribe(data => {
      this.users = data;
    })
  }
}
