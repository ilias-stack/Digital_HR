import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../../services/employees.service";
import {Employee} from "../../models/employee.model";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css'
})
export class EmployeesComponent implements OnInit{
  employees:any;
  errorMessage!:string;
  constructor(private employeeService:EmployeesService) {
  }

    ngOnInit(): void {
      this.employeeService.getEmployees().subscribe({
        next : (value) => {

          this.employees=value;
          console.log(value);

        },
        error : err => {
          this.errorMessage=err.message;
        }
      })
    }

}
