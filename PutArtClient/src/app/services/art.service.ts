import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArtService {

  constructor(
    private http: HttpClient
  ) { }

  getArtworks(city: string): Observable<String[]> {
    return this.http.get<String[]>('/PutArtApart/art/city/' + city);
  } 

  getMuseum(artwork: string): Observable<Museum> {
    return this.http.get<Museum>('/PutArtApart/art/artwork/' + artwork);
  }
}

export class Museum {
  
  'name': string;
  'latitude': number;
  'longitude': number;

}