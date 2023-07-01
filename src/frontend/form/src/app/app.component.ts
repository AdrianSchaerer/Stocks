import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { saveAs } from 'file-saver';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ReactiveForms';
  reactiveForm: FormGroup;
  apiResponse: any;
  startDate: string;
  endDate: string;
  stockName: string;
  startValue: number;
  endValue: number;
  investValue: number;
  finalValue: number;
  gainLossValue: number;



  constructor(private http: HttpClient) {}



  ngOnInit() {
    this.reactiveForm = new FormGroup({
      investValue: new FormControl(null, [Validators.required, Validators.min(1), Validators.pattern('[0-9]*')]),
      startDate: new FormControl(null, Validators.required),
      endDate: new FormControl(null, Validators.required),
      stockName: new FormControl(null, Validators.required),
    });
  }



  onSubmit() {
    const formData = {
      investValue: this.reactiveForm.get('investValue').value,
      startDate: this.reactiveForm.get('startDate').value,
      endDate: this.reactiveForm.get('endDate').value,
      stockName: this.reactiveForm.get('stockName').value,
    };



    /** Replaced by the code below
     *
     this.http
      .post('http://localhost:8080/stocks', formData)
      .subscribe({
        next: (response) => console.log(response),
        error: (error) => console.log(error),
      });
     **/

    this.http
      .post('http://localhost:8080/stocks', formData)
      .subscribe(
        (response: any) => {
          this.apiResponse = response; // Store the response object
          this.extractValues(response); // Call a method to extract and work with the values
        },
        (error) => {
          console.log(error);
        }
      );
  }

  private extractValues(response: any) {
    this.startDate = response.startDate;
    this.endDate = response.endDate;
    this.stockName = response.stockName;
    this.startValue = response.startValue;
    this.endValue = response.endValue;
    this.investValue = response.investValue;
    this.finalValue = response.finalValue;
    this.gainLossValue = response.gainLossValue;


    // Work with the extracted values as needed
    console.log(this.startDate);
    console.log(this.endDate);
    console.log(this.stockName);
    console.log(this.startValue);
    console.log(this.endValue);
    console.log(this.investValue);
    console.log(this.finalValue);
    console.log(this.gainLossValue);

    /** Not needed calculations are made in the backend
     *
    const profit = this.finalValue - this.investValue;
    const roi = (profit / this.investValue) * 100;

    console.log(profit);
    console.log(roi);
     **/

  }

  exportStockData(): void {
    const apiUrl = 'http://localhost:8080/exporter/export';
    this.http.post(apiUrl, {}, { responseType: 'text' })
      .subscribe(
        (response: string) => {
          // Handle successful response here
          console.log('CSV file URL:', response);
          this.downloadFile(response);
        },
        (error: any) => {
          // Handle error response here
          console.error('Error exporting stock data:', error);
        }
      );
  }

  private downloadFile(url: string): void {
    const link = document.createElement('a');
    link.href = url;
    link.download = url.substring(url.lastIndexOf('/') + 1);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }


}

