import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from '../models/task.model';
import { TaskHistory } from '../models/task-history.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseUrl: string = 'http://localhost:8080/tasks';

  constructor(private httpClient: HttpClient) { }

  getTasks(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(`${this.baseUrl}`);
  }

  getTaskById(id: number): Observable<Task> {
    return this.httpClient.get<Task>(`${this.baseUrl}/${id}`);
  }

  createTask(task: Task): Observable<Task> {
    return this.httpClient.post<Task>(this.baseUrl, task);
  }

  updateTask(id: number, task: Task): Observable<Task> {
    return this.httpClient.put<Task>(`${this.baseUrl}/${id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }
  getTaskHistory(id: number): Observable<TaskHistory[]> {
    return this.httpClient.get<TaskHistory[]>(`${this.baseUrl}/${id}/history`);
  }
}
