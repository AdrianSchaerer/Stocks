/** This file handles the routing of the application.
 */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {ResultsComponent} from "./results/results.component";


// TRD: The routes array contains the routes of the application. The first route is the home route, which is the route of the AppComponent.
// the second route is the results route, which is the route of the ResultsComponent.
const routes: Routes = [
 // { path: '', component: AppComponent }, // Home (Form) route
 // { path: 'results', component: ResultsComponent }, // Results site route
];

// TRD: The AppRoutingModule is the routing module of the application. It imports the RouterModule and defines the routes of the application.
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

