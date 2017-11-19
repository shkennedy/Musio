import { Component, OnInit } from '@angular/core';
import { Artist } from '../../models/artist.model'

@Component({
  selector: 'app-artist-view',
  templateUrl: './artist-view.component.html',
  styleUrls: ['./artist-view.component.css']
})
export class ArtistViewComponent implements OnInit {

  constructor(private artists:Artist[]) { }

  ngOnInit() {
  }

}
