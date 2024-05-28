import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectDTO, ProjectsService} from "../../services/projects.service";

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrl: './add-project.component.css'
})
export class AddProjectComponent {
  projectForm: FormGroup;

  constructor(private projectService: ProjectsService, private fb: FormBuilder) {
    this.projectForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      startDate: ['', Validators.required],
      estimatedEndDate: ['', Validators.required],
      projectStatus: ['', Validators.required],
      estimatedRevenue: ['', [Validators.required, Validators.min(0)]],
      customerRating: ['', [Validators.min(0), Validators.max(5)]],
      idCustomer: ['', Validators.required],
      employeeIds: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.projectForm.valid) {
      const formValues = this.projectForm.value;
      const projectDTO: ProjectDTO = {
        title: formValues.title,
        description: formValues.description,
        startDate: formValues.startDate,
        estimatedEndDate: formValues.estimatedEndDate,
        projectStatus: formValues.projectStatus,
        estimatedRevenue: formValues.estimatedRevenue,
        customerRating: formValues.customerRating,
      };
      const idCustomer = formValues.idCustomer;
      const employeeIds = formValues.employeeIds.split(',').map((id: string) => parseInt(id, 10));

      this.projectService.addProject(projectDTO, idCustomer, employeeIds).subscribe(response => {
        console.log('Project added successfully', response);
        // Handle successful response, e.g., show a notification or redirect
      }, error => {
        console.error('Error adding project', error);
        // Handle error response
      });
    }
  }
}
