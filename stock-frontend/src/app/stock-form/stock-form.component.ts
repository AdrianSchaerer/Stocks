import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-stock-form',
  templateUrl: './stock-form.component.html',
  styleUrls: ['./stock-form.component.css']
})
export class StockFormComponent {
  formData = {
    startDate: '',
    endDate: '',
    stockName: '',
    investmentValue: 0
  };

  constructor(private http: HttpClient) {}

  submitForm() {
    this.http.post<any>('http://localhost:8080/calculate', this.formData)
      .subscribe(response => {
        console.log(response);
        // Handle the response data as needed
      });
  }
}
