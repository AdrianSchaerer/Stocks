/** The AppModule is the root module of the application.
 * It imports the BrowserModule, AppRoutingModule, ReactiveFormsModule, and HttpClientModule.
 * */

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { ResultsComponent } from './results/results.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';

// TRD: The AppModule is the root module of the application. It imports the BrowserModule, AppRoutingModule, ReactiveFormsModule, and HttpClientModule.
@NgModule({
  declarations: [
    AppComponent,
    ResultsComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
