import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArtistService } from '../../../services/artist.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Artist } from '../../../models/artist.model';
import { Album } from '../../../models/album.model';
import { Genre } from '../../../models/genre.model';
import { Song } from '../../../models/song.model';

@Component({
  selector: 'artist-detail',
  templateUrl: './artist-detail.component.html',
  styleUrls: ['./artist-detail.component.css'],
  providers: [ArtistService, FavoritesService]
})
export class ArtistDetailComponent implements OnInit {

  @Input() artistId: number;

  private artist:Artist;

  constructor(
    private router: Router,
    private artistService: ArtistService,
    private favoritesService: FavoritesService
  ) { }

  ngOnInit() {
    this.artistService.getArtistById(this.artistId)
    .subscribe((artist: Artist) => {
      this.artist = artist;
    });
  }
}
