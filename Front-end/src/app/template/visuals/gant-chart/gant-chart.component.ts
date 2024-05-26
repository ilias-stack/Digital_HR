import { Component, Inject, Input, OnInit } from '@angular/core';
import {
  Chart,
  ChartData,
  ChartOptions,
  ChartType,
  registerables,
} from 'chart.js';
import 'chartjs-adapter-moment';
import { sample } from 'rxjs';
import { GantTaskModel } from './task.model';

@Component({
  selector: 'app-gant-chart',
  templateUrl: './gant-chart.component.html',
  styleUrl: './gant-chart.component.css',
})
export class GantChartComponent implements OnInit {
  @Input() barChartLabels!: string[];
  @Input() barChartData!: number[];
  @Input() barChartTitle!: string;

  public barChartAll: ChartData<'line'> = {
    labels: ['a', 's', 'f', '2', 'a', 's', 'f', '2'],
    datasets: [
      {
        label: '',
        data: [10, 20, 30, 10, 10, 20, 30, 10],
        backgroundColor: [
          '#EF4444',
          '#4F46E5',
          '#8B5CF6',
          '#FCD34D',
          '#4199E1',
          '#6B7280',
          '#AD8755',
          '#AA6439',
          '#CC6699',
          '#90EE90',
        ],
      },
    ],
  };

  sampleData: GantTaskModel[] = [
    { label: 'Task 1', start: '2022-02-01', end: '2022-02-03' },
    { label: 'Task 2', start: '2022-02-04', end: '2022-02-06' },
    { label: 'Task 3', start: '2022-02-07', end: '2022-02-10' },
  ];

  public barChartOptions: ChartOptions = {
    indexAxis: 'y',
    responsive: true,
    scales: {
      x: {
        type: 'time',
        time: {
          unit: 'day',
        },
      },
      y: {
        grid: {
          display: false,
        },
      },
    },
  };

  constructor() {
    Chart.register(...registerables);
  }

  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  ngOnInit() {
    const datasetData = this.sampleData.map((task) => [
      { x: new Date(task.start).getTime(), y: task.label },
      { x: new Date(task.end).getTime(), y: task.label },
    ]);
    this.barChartAll.datasets[0].data = datasetData.flat().map((itemi) => {
      return itemi.x;
    });
    this.barChartAll.labels = datasetData.flat().map((itemi) => {
      return itemi.y;
    });
  }
}
