import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
// Logo is loaded. If not, show headings instead:
  isLogoLoaded = false;
  hideHeadings() {
    this.isLogoLoaded = true;
  }
  showHeadings() {
    this.isLogoLoaded = false;
  }
}
