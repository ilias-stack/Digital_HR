import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';
import { RelevancyItem } from '../../models/relevancy.model';

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

  projectsGraphData!: [string, number][];
  projectRelevantList!: RelevancyItem[];

  tasksGraphData!: [string, number][];
  taskRelevantList!: RelevancyItem[];

  getCategories(data: [string, number][]) {
    return data.map((item) => item[0]);
  }

  getCounts(data: [string, number][]) {
    return data.map((item) => item[1]);
  }

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    //! Counts initialisation
    this.dashboardService.getCounts().subscribe(
      (data) => (this.counts = data),
      (error) => console.error('Error fetching counts:', error)
    );
    this.initialiseGraphs();
  }

  initialiseGraphs(): void {
    const projectsList: RelevancyItem[] = [];
    const tasksList: RelevancyItem[] = [];
    this.dashboardService.getCountProjectsByStatus().subscribe((data) => {
      this.projectsGraphData = data;
    });

    this.dashboardService
      .getIncompleteTaskCountPerProject()
      .subscribe((data) => {
        this.tasksGraphData = data;
      });

    this.dashboardService.getCloseDueTasks().subscribe((data) => {
      (data as []).forEach((element) => {
        const { description, endDate, taskProgress } = element;
        tasksList.push({
          title: description,
          endDate,
          status: taskProgress,
        });
      });
      this.taskRelevantList = tasksList;
    });

    this.dashboardService.getCloseDueProjects().subscribe((data) => {
      (data as []).forEach((element) => {
        const { title, endDate, projectStatus } = element;
        projectsList.push({
          title,
          endDate,
          status: projectStatus,
        });
      });
      this.projectRelevantList = projectsList;
    });
  }
}
