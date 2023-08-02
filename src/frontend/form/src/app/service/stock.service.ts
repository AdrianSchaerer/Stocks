import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { CustomResponse } from "../interface/custom-response";
import { Observable, throwError} from "rxjs";
import { catchError, tap} from "rxjs/operators";
import { Stock } from "../interface/stock";

@Injectable({providedIn: 'root'})
export class StockService {

  private readonly apiUrl = 'http://localhost:8080/StocksNew';

  constructor(private http:HttpClient) { }

  stocks$ = this.http.get<CustomResponse>(`${this.apiUrl}/list`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  ) as Observable<CustomResponse>;

  save$ = (stock: Stock) => this.http.post<CustomResponse>(`${this.apiUrl}/save`, stock)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    ) as Observable<CustomResponse>;

  // filter$ = (status: Status, response: CustomResponse) => new Observable<CustomResponse>(
  //   subscriber => {
  //     console.log(response);
  //     subscriber.next(
  //       status === Status.ALL ? { ...response, message: `Stocks filtered by ${status}` status} :
  //       {
  //         ...response,
  //         message: response.data.stocks
  //         .filter(stock => stock.status === status).length > 0 ? `Stocks filtered by
  //         ${status === Status.SERVER_UP ? 'SERVER UP': 'SERVER DOWN'} status`: `No stocks with ${status} found`,
  //         data: { stocks: response.data.stocks
  //             .filter(stock => stock.status === status)
  //       }
  //     );
  //     subscriber.complete();
  //   }
  // )
  // .pipe(
  //     tap(console.log),
  //     catchError(this.handleError)
  //   ) as Observable<CustomResponse>;

  delete$ = (stockId: number) => this.http.delete<CustomResponse>(`${this.apiUrl}/delete/${stockId}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    ) as Observable<CustomResponse>;

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    return throwError (`An error occurred - Error code: ${error.status}`);
  }
}
