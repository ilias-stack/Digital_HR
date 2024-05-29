import { Component, Input, OnInit } from '@angular/core';
import {
  Chart,
  ChartData,
  ChartOptions,
  ChartType,
  plugins,
  registerables,
} from 'chart.js';
import 'chartjs-adapter-moment';

@Component({
  selector: 'app-gant-chart',
  templateUrl: './gant-chart.component.html',
  styleUrls: ['./gant-chart.component.css'],
})
export class GantChartComponent implements OnInit {
  @Input() barChartLabels!: string[];
  @Input() barChartData!: [number, number][];
  @Input() barChartTitle!: string;

  public barChartAll: ChartData<'bar'> = {
    labels: [],
    datasets: [
      {
        data: [],
        borderRadius: 6,
        borderSkipped: false,
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

  todayDate = new Date();

  public barChartOptions: ChartOptions<'bar'> = {
    indexAxis: 'y',
    responsive: true,
    scales: {
      x: {
        type: 'time',
        min: new Date(
          this.todayDate.getFullYear() - 1,
          this.todayDate.getMonth(),
          this.todayDate.getDate()
        ).getTime(),
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
    this.barChartAll.labels = this.barChartLabels;
    this.barChartAll.datasets[0].data = this.barChartData;
    this.barChartAll.datasets[0].label = this.barChartTitle;
  }
}
