import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeesComponent } from './content/employees/employees.component';
import { ProjectsComponent } from './content/projects/projects.component';
import { CustomersComponent } from './content/customers/customers.component';
import { HomeComponent } from './home/home.component';
import { PhishingComponent } from './phishing/phishing.component';
import { DashboardComponent } from './content/dashboard/dashboard.component';
import { CvScannerComponent } from './content/cv-scanner/cv-scanner.component';
import {AdminComponent} from "./admin/admin.component";
import {LoginComponent} from "./login/login.component";
import {AddProjectComponent} from "./content/add-project/add-project.component";
import {ChatbotComponent} from "./content/chatbot/chatbot.component";

const routes: Routes = [
  {path :"admin", component : AdminComponent, children:[
      { path: 'employees', component: EmployeesComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'cv_scanner', component: CvScannerComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'projects', component: ProjectsComponent },
      { path: 'customers', component: CustomersComponent },
      { path: 'phishing', component: PhishingComponent },
      {path:"addProject",component:AddProjectComponent},
      {path:"chatBot",component:ChatbotComponent}
    ]},
  { path: 'home', component: HomeComponent },
  {path:"",redirectTo:"home",pathMatch:"full"},
  {path:"login", component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
