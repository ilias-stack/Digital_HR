import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "../models/employee.model";
import {Project} from "../models/project.model";
export interface ProjectDTO {
  id?: number;
  title: string;
  description: string;
  startDate: Date;
  estimatedEndDate: Date;
  projectStatus: string;
  endDate?: Date;
  estimatedRevenue: number;
  customerRating?: number;
}
@Injectable({
  providedIn: 'root'
})

export class ProjectsService {

  backendHost:string="http://localhost:8085"
  constructor(private  http:HttpClient) { }
  addProject(projectDTO: ProjectDTO, idCustomer: number, employeeIds: number[]): Observable<ProjectDTO> {
    return this.http.post<ProjectDTO>(this.backendHost+"/projects", projectDTO, {
      params: {
        idCustomer: idCustomer.toString(),
        employeeIds: employeeIds.join(',')
      }
    });
  }

  public getProjects():Observable<Array<Project>>{
    return this.http.get<Array<Project>>(this.backendHost+"/projects");
  }
}
