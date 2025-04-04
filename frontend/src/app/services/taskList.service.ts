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
  private readonly taskListUrl = 'http://localhost:8080/taskList';
  private readonly taskUrl = 'http://localhost:8080/task';

  private readonly http=inject(HttpClient);

  getAllTaskLists(boardId: string): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(`${this.taskListUrl}/taskListByBoardId/${boardId}`);
    }
  
  getTaskByTaskListId(taskListId: string): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(`${this.taskUrl}/taskByTaskListId/${taskListId}`);
  }
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
