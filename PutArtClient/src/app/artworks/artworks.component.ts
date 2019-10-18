import { Component, OnInit, Input, OnChanges } from "@angular/core";
import { ArtService } from "../services/art.service";

@Component({
  selector: "app-artworks",
  templateUrl: "./artworks.component.html",
  styleUrls: ["./artworks.component.css"]
})
export class ArtworksComponent implements OnChanges {
  artworksList: String[]; /* = [
    "Trivulzio_Madonna",
    "San_Luca_Altarpiece",
    "St._Bernardino_of_Siena_between_Two_Angels",
    "Adoration_of_the_Magi_(Correggio)",
    "Lamentation_over_the_Dead_Christ_with_Saints_(Botticelli)",
    "The_Kiss_(Hayez)",
    "Development_of_a_Bottle_in_Space",
    "St._Mark_Preaching_in_Alexandria",
    "Brera_Madonna",
    "Portrait_of_Andrea_Doria_as_Neptune",
    "Adoration_of_the_Magi_(Stefano_da_Verona)",
    "Saint_Jerome_in_Penitence_(Titian,_1552)",
    "Basket_of_Fruit_(Caravaggio)",
    "Lamentation_of_Christ_(Mantegna)",
    "Madonna_of_the_Book",
    "Supper_at_Emmaus_(Caravaggio),_Milan",
    "Finding_of_the_body_of_St_Mark",
    "Unique_Forms_of_Continuity_in_Space",
    "The_Marriage_of_the_Virgin_(Raphael)",
    "Valle_Romita_Polyptych",
    "Last_Supper_(Rubens)",
    "Santa_Maria_in_Porto_Altarpiece"
  ];*/
  artwork: string;

  constructor(private artService: ArtService) {}

  @Input() city: string;

  ngOnChanges() {
    console.log(this.city);
    console.log(this.artworksList);

    this.artwork = null;
    this.artService.getArtworks(this.city).subscribe(artworks => {
      this.artworksList = artworks;
      //if(artworks.length = 0) {
      //  this.artwork="Non ci sono opere d'arte in questa città!";
      //}
      console.log(this.artworksList);
    },
    error => {
      //this.artworksList = [];
      this.artwork="Non ci sono opere d'arte in questa città!"});
  }

  go(event) {
    console.log(event);
    this.artwork = event;
  }
}
