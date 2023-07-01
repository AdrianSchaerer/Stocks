import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent {
  @Input() startDate: string;
  @Input() endDate: string;
  @Input() stockName: string;
  @Input() startValue: number;
  @Input() endValue: number;
  @Input() investValue: number;
  @Input() finalValue: number;
  @Input() gainLossValue: number;
}
