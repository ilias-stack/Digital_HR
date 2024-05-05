import {Injectable, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Employe} from "../models/employe.model";

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  backendHost:string="http://localhost:8085"
  constructor(private  http:HttpClient) { }

  public getEmployees():Observable<Array<Employe>>{
    return this.http.get<Array<Employe>>(this.backendHost+"/employees");
  }

}
