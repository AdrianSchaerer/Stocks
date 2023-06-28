import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ReactiveForms';
  reactiveForm: FormGroup;



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



    this.http
      .post('http://localhost:8080/stocks', formData)
      .subscribe({
        next: (response) => console.log(response),
        error: (error) => console.log(error),
      });
  }
}


