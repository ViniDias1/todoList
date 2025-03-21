import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/'; // URL do backend

  constructor(private http: HttpClient) { }
  
  getAllUsers(): Observable<string> {
    return this.http.get(this.apiUrl+"users/all", { responseType: 'text' });
  }

    login(user: { name: string; email: string }) {
      
      console.log("TA AI");
      return this.http.post(this.apiUrl+"users/login", user, { responseType: 'text' });

    }

}
