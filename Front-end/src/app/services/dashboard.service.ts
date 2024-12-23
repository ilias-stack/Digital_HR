import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../models/task.model';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private apiUrl = 'http://spring-boot-app:8085/dashboard';

  constructor(private http: HttpClient) {}

  getCounts(): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/counts');
  }

  getCountProjectsByStatus(): Observable<[string, number][]> {
    return this.http.get<[string, number][]>(
      this.apiUrl + '/countProjectsByStatus'
    );
  }

  getCloseDueProjects(): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/findProjectsCloseToCurrentDate');
  }

  getIncompleteTaskCountPerProject(): Observable<[string, number][]> {
    return this.http.get<[string, number][]>(
      this.apiUrl + '/findIncompleteTaskCountPerProject'
    );
  }

  getCloseDueTasks(): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/findTasksCloseToCurrentDate');
  }

  getEmployeeCountPerProject(): Observable<[string, number][]> {
    return this.http.get<[string, number][]>(
      this.apiUrl + '/findEmployeeCountPerProject'
    );
  }

  getGanttData(): Observable<Task[]> {
    return this.http.get<Task[]>(
      this.apiUrl + '/findPendingTasksOrderedByStartDate'
    );
  }
}
