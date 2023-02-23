import { HttpClient } from '@angular/common/http';
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
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }
}
