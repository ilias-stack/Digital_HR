import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmployeesComponent} from "./content/employees/employees.component";
import {ProjectsComponent} from "./content/projects/projects.component";
import {CustomersComponent} from "./content/customers/customers.component";
import {HomeComponent} from "./home/home.component";
import {PhishingComponent} from "./phishing/phishing.component";

const routes: Routes = [{path:"employees",component:EmployeesComponent},
  {path:"projects",component:ProjectsComponent},
  {path:"customers",component:CustomersComponent},
  {path:"home",component:HomeComponent},
  {path:"phishing",component:PhishingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
