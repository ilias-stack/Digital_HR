import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  counts: {
    totalCustomers: number;
    totalEmployees: number;
    totalProjects: number;
  } = { totalCustomers: 0, totalEmployees: 0, totalProjects: 0 };

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    //! Counts initialisation
    this.dashboardService.getCounts().subscribe(
      (data) => (this.counts = data),
      (error) => console.error('Error fetching counts:', error)
    );
  }
}
