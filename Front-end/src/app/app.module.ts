import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './template/navbar/navbar.component';
import { SidebarComponent } from './template/sidebar/sidebar.component';
import { EmployeesComponent } from './content/employees/employees.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { ProjectsComponent } from './content/projects/projects.component';
import { CustomersComponent } from './content/customers/customers.component';
import { HomeComponent } from './home/home.component';
import { PhishingComponent } from './phishing/phishing.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    EmployeesComponent,
    ProjectsComponent,
    CustomersComponent,
    HomeComponent,
    PhishingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
