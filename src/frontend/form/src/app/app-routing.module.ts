import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {ResultsComponent} from "./results/results.component";



const routes: Routes = [
 // { path: '', component: AppComponent }, // Home (Form) route
 // { path: 'results', component: ResultsComponent }, // Results site route
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

