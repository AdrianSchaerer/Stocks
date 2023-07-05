import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';


// TRD: root component of the application
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

// TRD: implements OnInit interface to initialize the component with a form
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


// TRD: inject the HttpClient service
  constructor(private http: HttpClient) {}


// TRD: In this method, we initialize the reactiveForm property with a new instance of the FormGroup class.
  ngOnInit() {
    this.reactiveForm = new FormGroup({
      investValue: new FormControl(null, [Validators.required, Validators.min(1), Validators.pattern('[0-9]*')]),
      startDate: new FormControl(null, Validators.required),
      endDate: new FormControl(null, Validators.required),
      stockName: new FormControl(null, Validators.required),
    });
  }


// TRD: The onSubmit method is a function that is called when the form is submitted.
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

    // TRD: Make the POST Call to the backend to get the data from the API.
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

  // TRD: Extract the values from the response object and stores them in the component properties.
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
// FEM: Export the stock data to a CSV file
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

  // FEM: Download the CSV file
  private downloadFile(url: string): void {
    const link = document.createElement('a');
    link.href = url;
    link.download = url.substring(url.lastIndexOf('/') + 1);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

// TRD: Refresh the page to start over
  refreshPage() {
    window.location.reload();
  }

// Logo is loaded. If not, show headings instead:
  isLogoLoaded = false;

  hideHeadings() {
    this.isLogoLoaded = true;
  }

  showHeadings() {
    this.isLogoLoaded = false;
  }
}

