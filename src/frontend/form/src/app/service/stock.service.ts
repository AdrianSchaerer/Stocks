import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CustomResponse} from "../interface/custom-response";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Stock} from "../interface/stock";
import {Status} from "../enum/status.enum";

@Injectable({providedIn: 'root'})
export class StockService {

  private readonly apiUrl = 'http://localhost:8080/stocksNew';

  constructor(private http: HttpClient) {
  }

  stocks$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/list`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  save$ = (stock: Stock) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/save`, stock)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  filter$ = (status: Status, response: CustomResponse) => <Observable<CustomResponse>>
    new Observable<CustomResponse>(
      subscriber => {
        console.log(response);
        subscriber.next(
          status === Status.ALL ?
            {...response, message: `Stocks filtered by ${status} status`} :
            {
              ...response, message: response.data.stocks
                .filter(stock => stock.status === status).length > 0 ?
                `Stocks filtered by ${status === Status.GAIN_POS ? 'GAIN POS' : 'GAIN NEG'} status` :
                `No stocks with ${status} found`,
              data: {
                stocks: response.data.stocks
                  .filter(stock => stock.status === status)
              }
            }
        );
        subscriber.complete();
      }
    )
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (stockId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/delete/${stockId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
