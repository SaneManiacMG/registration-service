import { ClientResponse } from './../model/client-response.model';
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

  registerNewUser(email: string) : Observable<ClientResponse> {
    return this.http.post<ClientResponse>("/register", email);
  }

  loginUser(user: User) : Observable<ClientResponse> {
    return this.http.post<ClientResponse>("/login", user);
  }
}
