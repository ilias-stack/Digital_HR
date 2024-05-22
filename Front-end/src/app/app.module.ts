import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './template/navbar/navbar.component';
import { SidebarComponent } from './template/sidebar/sidebar.component';
import { EmployeesComponent } from './content/employees/employees.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ProjectsComponent } from './content/projects/projects.component';
import { CustomersComponent } from './content/customers/customers.component';
import { HomeComponent } from './home/home.component';
import { PhishingComponent } from './phishing/phishing.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './content/dashboard/dashboard.component';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { BarChartComponent } from './template/visuals/bar-chart/bar-chart.component';
import { GantChartComponent } from './template/visuals/gant-chart/gant-chart.component';
import { CvScannerComponent } from './content/cv-scanner/cv-scanner.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    EmployeesComponent,
    ProjectsComponent,
    CustomersComponent,
    HomeComponent,
    PhishingComponent,
    DashboardComponent,
    BarChartComponent,
    GantChartComponent,
    CvScannerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [provideCharts(withDefaultRegisterables())],
  bootstrap: [AppComponent],
})
export class AppModule {}
