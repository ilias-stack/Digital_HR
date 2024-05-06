import {Injectable, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Employee} from "../models/employee.model";



@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  backendHost:string="http://localhost:8085"
  constructor(private  http:HttpClient) { }

  public getEmployees():Observable<Array<Employee>>{
    return this.http.get<Array<Employee>>(this.backendHost+"/employees");
  }

}
