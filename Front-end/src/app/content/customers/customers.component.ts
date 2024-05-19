import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../../services/employees.service";
import {CustomersService} from "../../services/customers.service";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers:any;
  errorMessage!:string;
  constructor(private customersService:CustomersService) {
  }

  ngOnInit(): void {
    this.customersService.getCustomers().subscribe({
      next : (value) => {

        this.customers=value;
        console.log(value);

      },
      error : err => {
        this.errorMessage=err.message;
      }
    })
  }}
