import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../../services/employees.service";
import {data} from "autoprefixer";
import {Employe} from "../../models/employe.model";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css'
})
export class EmployeesComponent implements OnInit{
  employees:any;
  data:Employe = {employeeName:"soulaimane",email:"soulaimane",employeeNumber:10,performanceScore:10};
  errorMessage!:string;
  constructor(private employeService:EmployeesService) {
  }

    ngOnInit(): void {
      this.employeService.getEmployees().subscribe({
        next : (value) => {

          this.employees=value;
          this.employees=this.data;
          console.table(value);

        },
        error : err => {
          this.errorMessage=err.message;
        }
      })
    }

}
