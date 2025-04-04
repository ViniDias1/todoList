import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


interface TaskList {
  id: string;
  title: string;
  boardId: string;
}

@Injectable({
  providedIn: 'root',
})
export class TaskListService {
  private readonly baseUrl = 'http://localhost:8080/taskList';

  private readonly http=inject(HttpClient);

  getAllTaskLists(boardId: string): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(`${this.baseUrl}/taskListByBoardId/${boardId}`);
    }

//   createBoard(board: Board): Observable<Board> {
//     return this.http.post<Board>(this.baseUrl + "/createBoard", board);
//   }

//   deleteBoard(id: string): Observable<string> {
//     return this.http.delete(this.baseUrl + "/deleteBoard/" + id, { responseType: 'text' });
//   }
  
  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  getAuthenticatedHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }
}
