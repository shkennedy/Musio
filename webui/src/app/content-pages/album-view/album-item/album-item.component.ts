import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';


import { Album } from '../../../models/album.model'
@Component({
  selector: 'album-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css'],
  providers: [SongService, FavoritesService]
})
export class AlbumItemComponent implements OnInit {
  @Input() album: Album;
  //remove this later
  private fake:boolean = false;
  constructor() { }

  model: {
    song: Song
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
