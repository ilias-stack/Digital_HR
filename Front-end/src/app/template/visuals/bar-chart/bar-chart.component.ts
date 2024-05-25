import { Component, Input, OnInit } from '@angular/core';
import { ChartData, ChartOptions, ChartType } from 'chart.js';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css'],
})
export class BarChartComponent implements OnInit {
  @Input() barChartLabels!: string[];
  @Input() barChartData!: number[];
  @Input() barChartTitle!: string;

  public barChartAll: ChartData<'line'> = {
    labels: [],
    datasets: [
      {
        label: '',
        data: [],
        backgroundColor: [
          'rgba(239, 68, 68, 0.5)',
          'rgba(79, 70, 229, 0.5)',
          'rgba(139, 92, 246, 0.5)',
          'rgba(252, 211, 77, 0.5)',
          'rgba(66, 153, 225, 0.5)',
          'rgba(107, 114, 128, 0.5)',
          'rgba(173, 135, 85, 0.5)',
          'rgba(170, 100, 57, 0.5)',
          'rgba(204, 102, 153, 0.5)',
          'rgba(144, 238, 144, 0.5)',
        ],
        borderColor: [
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
        borderWidth: 1,
      },
    ],
  };

  public barChartOptions: ChartOptions = {
    responsive: true,
  };
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  constructor() {}

  ngOnInit() {
    this.barChartAll.labels = this.barChartLabels;
    this.barChartAll.datasets[0].data = this.barChartData;
    this.barChartAll.datasets[0].label = this.barChartTitle;
  }
}
