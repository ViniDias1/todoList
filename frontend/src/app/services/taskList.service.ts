import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


interface TaskList {
  id: string;
  name: string;
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

  moveTask(task: any, destinationListId: string): void {
  const deleteUrl = `${this.taskUrl}/deleteTask/${task.id}`;
  const createUrl = `${this.taskUrl}/createTask`;

  this.http.delete(deleteUrl).subscribe({
    next: () => {
      console.log('Task successfully deleted from original list.');
    },
    error: (error) => {
      console.error('Error deleting task:', error);
    }
  });

  task.listId = destinationListId;

  this.http.post(createUrl, task).subscribe({
    next: () => {
      console.log('Task successfully added to new list.');
    },
    error: (error) => {
      console.error('Error adding task to new list:', error);
    }
  });
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
