import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { SongService } from '../../../../../services/song.service';
import { FavoritesService } from '../../../../../services/favorites.service';

import { Song } from '../../../../../models/song.model';

@Component({
  selector: 'song-item',
  templateUrl: './song-item.component.html',
  styleUrls: ['./song-item.component.css'],
  providers: [SongService, FavoritesService]
})
export class SongItemComponent implements OnInit {

  @Input() song: Song;

  constructor(
    private router: Router,
    private favoritesService: FavoritesService,
    private songService: SongService
  ) { }

  ngOnInit() {
    // this.albumService.getAlbumById(this.albumId)
    // .subscribe((album: Album) => {
    //   this.model.album = album;
    // });
  }
}
