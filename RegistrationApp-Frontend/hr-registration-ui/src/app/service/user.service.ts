import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsersList() : Observable<User[]> {
    return this.http.get<User[]>('/home');

  }

  registerNewUser(email: string) {
    console.log("posting email...")
    return this.http.post("/register", email);
  }
}
