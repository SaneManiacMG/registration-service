import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

private baseURL = "localhost:8080/home";

  constructor(private httpClient: HttpClient) { }

  getUsersList() : Observable<User[]> {
    return this.httpClient.get<User[]>('/home');

  }

  registerNewUser(email: string) : any {
    console.log("sending request...");
    const headers = new HttpHeaders({ 'Content-Type': 'text/plain' })
    const options = { headers: headers };
    return this.httpClient.post<string>('/register', email, { headers });
  }
}
