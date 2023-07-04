# Stock Search Engine
> The Stock Search Engine is a user-friendly tool developed using Angular and Spring Boot. It offers a simple and intuitive interface for users to enter stock details and dates, enabling the retrieval of historical stock values from the Twelvedata.com API. In addition to performing calculations for gains, losses, and final values, the backend now includes a convenient feature that allows users to export the retrieved data to a CSV file. This functionality empowers users to further analyze the historical stock data and seamlessly integrate it into their own workflows.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Packages and Dependencies] (#packages-and-dependencies)
* [Acknowledgements](#acknowledgements)
* [License](#license)
<!-- * [License](#license) -->


## General Information
- Purpose of the project:
Develop a simple and intuitive stock search engine with API integration, allowing users to retrieve historical stock data and export it to a CSV file.
- Reason for undertaking the project:
To leverage the ease of working with APIs, generating files, and implementing front-end and back-end solutions.
- Problem it intends to solve:
Provide a fast and user-friendly solution for obtaining historical stock data, facilitating stock analysis processes.

## Technologies used
- Angular (Frontend)
- Java Spring Boot (Backend)


## Features
The following features are currently implemented:
- Stock Search Frontend with search form.
- Stock Search Frontend result page.
- Stock Search process and calculation if final Value and gain/loss.
- API Backend for API Calls to Twelvedata.com for stock data retrieval.
- CSV File-Exporter Feature.


## Setup
Clone this repo to your System and run npm install to install all the dependencies.

## Usage
After you clone this repo to your System, go to its root directory and run npm install to install its dependencies.

Once the dependencies are installed, you can run npm start to start the Backend application. With the ng serve command, the Frontend service is started.
You will then be able to access it at https://localhost:4200

Make sure port 8080 is also open. It's used for the backend communication.


## Project Status
Project is: _in progress_ / _no longer being worked on_.
The project was under active development, but will probably not be continued after ZHAW project submission.


## Room for Improvement
Include areas you believe need improvement / could be improved. Also add TODOs for future development.

Room for improvement:
- Further code streamlining
- Improvement of test methods

To do:
- Final implementation (front- and backend) of File Import Feature.
- Implementation of multiple API Calls.
- Implementation of dynamic Chart.
- Re-Add the Database feature.
- Add a user and registration base.
- Add HTTPS security - and security overall. But Security was not part of the lecture. ;)

## Packages and Dependencies:
### Frontend:
- Spring Boot: Version 3.1.0
- Java: Version 17
- Spring Boot Starter Data REST
- Spring Boot Starter Web
- Spring Boot Starter Web Services
- Spring Boot Starter WebFlux
- Spring Session Core
- Spring Boot DevTools
- Spring Boot Configuration Processor
- Spring Boot Starter Test
- Project Reactor Test
- OpenCSV: Version 5.7.1
- Jackson Databind

### Backend:
@angular/animations: ^16.0.0
@angular/common: ^16.0.0
@angular/compiler: ^16.0.0
@angular/core: ^16.0.0
@angular/forms: ^16.0.0
@angular/platform-browser: ^16.0.0
@angular/platform-browser-dynamic: ^16.0.0
@angular/router: ^16.0.0
angular-chart.js: ^1.1.1
chart.js: ^2.9.4
rxjs: ~7.8.0
tslib: ^2.3.0
zone.js: ~0.13.0
@angular-devkit/build-angular: ^16.0.4
@angular/cli: ~16.0.4
@angular/compiler-cli: ^16.0.0
@types/chart.js: ^2.9.31
@types/jasmine: ~4.3.0
jasmine-core: ~4.6.0
karma: ~6.4.0
karma-chrome-launcher: ~3.2.0
karma-coverage: ~2.2.0
karma-jasmine: ~5.1.0
karma-jasmine-html-reporter: ~2.0.0
typescript: ~5.0.2

## Acknowledgements
- Our sincere gratitude to Matthias Bachmann for his invaluable contribution as a lecturer.

## License
Copyright (c) Adrian Hauser, Dominic Troll and Manuel Ferretti

MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software. However, if the Software is sold, the authors shall receive a fair share of the proceeds, to be mutually agreed upon.

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.