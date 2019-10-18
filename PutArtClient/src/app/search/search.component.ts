import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  constructor() { }

  private city: string

  searchArtworks(event) {
    // console.log(event);
    this.city = event;
  }

}
