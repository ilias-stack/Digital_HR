import { Component, Input } from '@angular/core';
import { ChartData, ChartOptions, ChartType } from 'chart.js';

@Component({
  selector: 'app-gant-chart',
  templateUrl: './gant-chart.component.html',
  styleUrl: './gant-chart.component.css',
})
export class GantChartComponent {
  @Input() barChartLabels!: string[];
  @Input() barChartData!: number[];
  @Input() barChartTitle!: string;

  public barChartAll: ChartData<'line'> = {
    labels: ['', '', '', ''],
    datasets: [
      {
        label: '',
        data: [10, 20, 30, 10],
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

  public barChartOptions: ChartOptions = {
    indexAxis: 'y',
    responsive: true,
  };
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  constructor() {}

  ngOnInit() {
    // this.barChartAll.labels = this.barChartLabels;
    // this.barChartAll.datasets[0].data = this.barChartData;
    // this.barChartAll.datasets[0].label = this.barChartTitle;
  }
}
