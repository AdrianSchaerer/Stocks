/** This file in TypeScript contains the code for the results component of the application.
 * It contains the code to display the results of the stock investment.
 */

import { Component, Input, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
// import { Chart, ChartData, ChartOptions } from 'chart.js';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})

// TRD: The ResultsComponent is a child component of the AppComponent. It receives the data from the parent component and displays it in the template.
export class ResultsComponent implements AfterViewInit {
  @Input() startDate: string;
  @Input() endDate: string;
  @Input() stockName: string;
  @Input() startValue: number;
  @Input() endValue: number;
  @Input() investValue: number;
  @Input() finalValue: number;
  @Input() gainLossValue: number;

  response: any;

  constructor(private http: HttpClient) {}



  // TRD: The Chart on the web does not work.
//  @ViewChild('chartCanvas') chartCanvas: ElementRef;
//  private chart: Chart;

  ngAfterViewInit() {
//    this.createChart();
    this.http
      .get('http://localhost:8080/stocksNew/list')
      .subscribe((response:any) => {
        this.availableStocks = response.;
      });
  }

  // TRD: The createChart method creates a new Chart instance and renders it on the canvas element.
//  private createChart() {
//    const canvas: HTMLCanvasElement = this.chartCanvas.nativeElement;
//    const ctx = canvas.getContext('2d');
//
//    const data: ChartData = {
//      labels: ['Start', 'End'],
//      datasets: [{
//        label: 'Stock Value',
//        data: [this.startValue, this.endValue],
//        backgroundColor: 'rgba(0, 123, 255, 0.5)',
//        borderColor: 'rgba(0, 123, 255, 1)',
//        borderWidth: 1
//      }]
//    };

//    const options: ChartOptions = {
//      scales: {
//        y: {
//          beginAtZero: true
//        }
//      }
//    };

//    this.chart = new Chart(ctx, {
//      type: 'line',
//      data,
//      options
//    });
//  }
}
