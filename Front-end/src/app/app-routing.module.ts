import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeesComponent } from './content/employees/employees.component';
import { ProjectsComponent } from './content/projects/projects.component';
import { CustomersComponent } from './content/customers/customers.component';
import { HomeComponent } from './home/home.component';
import { PhishingComponent } from './phishing/phishing.component';
import { DashboardComponent } from './content/dashboard/dashboard.component';
import { CvScannerComponent } from './content/cv-scanner/cv-scanner.component';

const routes: Routes = [
  { path: 'employees', component: EmployeesComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'cv_scanner', component: CvScannerComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'projects', component: ProjectsComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'home', component: HomeComponent },
  { path: 'phishing', component: PhishingComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
