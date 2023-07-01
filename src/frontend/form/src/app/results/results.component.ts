import { Component, Input, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { Chart, ChartData, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements AfterViewInit {
  @Input() startDate: string;
  @Input() endDate: string;
  @Input() stockName: string;
  @Input() startValue: number;
  @Input() endValue: number;
  @Input() investValue: number;
  @Input() finalValue: number;
  @Input() gainLossValue: number;

  @ViewChild('chartCanvas') chartCanvas: ElementRef;
  private chart: Chart;

  ngAfterViewInit() {
    this.createChart();
  }

  private createChart() {
    const canvas: HTMLCanvasElement = this.chartCanvas.nativeElement;
    const ctx = canvas.getContext('2d');

    const data: ChartData = {
      labels: ['Start', 'End'],
      datasets: [{
        label: 'Stock Value',
        data: [this.startValue, this.endValue],
        backgroundColor: 'rgba(0, 123, 255, 0.5)',
        borderColor: 'rgba(0, 123, 255, 1)',
        borderWidth: 1
      }]
    };

    const options: ChartOptions = {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    };

    this.chart = new Chart(ctx, {
      type: 'line',
      data,
      options
    });
  }
}
