import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { SongService } from '../../../../services/song.service';
import { Song } from '../../../../models/song.model';

@Component({
  selector: 'album-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css'],
  providers: [SongService, FavoritesService]
})
export class AlbumItemComponent implements OnInit {

  @Input() song: Song;

  model: {
  };

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
