import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "../models/employee.model";
import {Customer} from "../models/customer.model";

@Injectable({
  providedIn: 'root',
})
export class CustomersService {
  backendHost: string = 'http://spring-boot-app:8085';
  constructor(private http: HttpClient) {}

  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(this.backendHost + '/customers');
  }
}
