import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArtistService } from '../../services/artist.service';
import { FavoritesService } from '../../services/favorites.service';

import { Artist } from '../../models/artist.model';
import { Album } from '../../models/album.model';
import { Genre } from '../../models/genre.model';
import { Song } from '../../models/song.model';

@Component({
  selector: 'app-artist-view',
  templateUrl: './artist-view.component.html',
  styleUrls: ['./artist-view.component.css'],
  providers: [ArtistService, FavoritesService]
})
export class ArtistViewComponent implements OnInit {

  @Input() artistId: number;

  model: {
    artist: Artist
  };

  constructor(
    private router: Router,
    private artistService: ArtistService,
    private favoritesService: FavoritesService
  ) { }

  ngOnInit() {
    this.artistService.getArtistById(this.artistId)
    .subscribe((artist: Artist) => {
      this.model.artist = artist;
    });
  }
}
