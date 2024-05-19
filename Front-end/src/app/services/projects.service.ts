import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "../models/employee.model";
import {Project} from "../models/project.model";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  backendHost:string="http://localhost:8085"
  constructor(private  http:HttpClient) { }

  public getProjects():Observable<Array<Project>>{
    return this.http.get<Array<Project>>(this.backendHost+"/projects");
  }
}
