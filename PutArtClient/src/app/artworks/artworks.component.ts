import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { ArtService } from "../services/art.service";

@Component({
  selector: "app-artworks",
  templateUrl: "./artworks.component.html",
  styleUrls: ["./artworks.component.css"]
})
export class ArtworksComponent implements OnChanges {
  artworksList: String[];
  artwork: string;
  empty: boolean;

  constructor(private artService: ArtService) {}

  @Input() city: string;

  ngOnChanges() {
    // console.log(this.city);
    // console.log(this.artworksList);

    this.artwork = null;
    this.artworksList = null;
    
    this.artService.getArtworks(this.city).subscribe(artworks => {
      this.artworksList = artworks;
      // console.log(this.artworksList);
    },
    error => {
      // this.artworksList = [];
      this.artwork="Non ci sono opere d'arte in questa città!"});
  }

  go(event) {
    // console.log(event);
    this.artwork = event;
  }
}
