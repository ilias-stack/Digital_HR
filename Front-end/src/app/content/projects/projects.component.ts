import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../../services/employees.service";
import {ProjectsService} from "../../services/projects.service";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css'
})
export class ProjectsComponent implements OnInit{
  projects:any;
  errorMessage!:string;
  nombreProjects!:number;
  constructor(private projectsService:ProjectsService) {
  }

  ngOnInit(): void {
    this.projectsService.getProjects().subscribe({
      next : (value) => {

        this.projects=value;
        console.log(value);
        this.nombreProjects=this.projects.length;

      },
      error : err => {
        this.errorMessage=err.message;
      }
    })
  }

}
