import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Board {
  id: string;
  ownerId: string;
  title: string;
}

@Injectable({
  providedIn: 'root',
})
export class BoardService {
  private readonly baseUrl = 'http://localhost:8080/board';

  constructor(private readonly http: HttpClient) {}

  getAllBoards(): Observable<Board[]> {
    const ownerId = localStorage.getItem('ownerId');
    return this.http.get<Board[]>(`${this.baseUrl}/myBoards/${ownerId}`);
  }
}
