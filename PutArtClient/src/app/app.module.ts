import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";
import { HomeComponent } from "./home/home.component";
import { SearchComponent } from "./search/search.component";
import { ArtworksComponent } from "./artworks/artworks.component";
import { MapComponent } from "./map/map.component";
import { AgmCoreModule } from "@agm/core";
import { AngularOpenlayersModule } from "ngx-openlayers";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchComponent,
    ArtworksComponent,
    MapComponent
  ],
  imports: [
    BrowserModule,
    AgmCoreModule.forRoot({
      apiKey: "AIzaSyAKHIINgquWns_TcUx12vSBtCT7_KF3DIU"
    }),
    AppRoutingModule,
    AngularOpenlayersModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
