import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { ArtService, Museum } from "../services/art.service";

@Component({
  selector: "app-map",
  templateUrl: "./map.component.html",
  styleUrls: ["./map.component.css"]
})
export class MapComponent implements OnChanges {
  @Input() artwork: string;

  private museo: Museum;
  private errore: string;

  constructor(private artService: ArtService) {}

  ngOnChanges() {
    // console.log(this.artwork);
    this.museo = null;
    this.errore = null;
    
    if(this.artwork != null) {
      this.artService.getMuseum(this.artwork).subscribe(data => {
        this.museo = data;
        // console.log("lat: " + this.museo.latitude + ", long: " + this.museo.longitude);
      },
      error => {this.errore="Non ho trovato corrispondenze per l'opera d'arte!"});
    }
  }
}
