import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { SongService } from '../../../services/song.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Song } from '../../../models/song.model';

@Component({
  selector: 'song-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css'],
  providers: [SongService, FavoritesService]
})
export class SongItemComponent implements OnInit {

  @Input() song: Song;

  model: {
    song: Song
  };

  constructor(
    private router: Router,
    private favoritesService: FavoritesService,
    private songService: SongService
  ) { }

  ngOnInit() {
    this.model.song = this.song;

    // this.albumService.getAlbumById(this.albumId)
    // .subscribe((album: Album) => {
    //   this.model.album = album;
    // });
  }
}
