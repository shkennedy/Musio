import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlbumService } from '../../services/album.service';
import { FavoritesService } from '../../services/favorites.service';

import { Album } from '../../models/album.model';

@Component({
  selector: 'app-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.css'],
  providers: [AlbumService, FavoritesService]
})
export class AlbumViewComponent implements OnInit {
    private favoritesService: FavoritesService
  constructor(
    private router: Router,
    private albumService: AlbumService,
  public albums:Album[]
    let a=new Album();
    a.album_name="cool album";
    a.artist_name="another bingus hinguson";
    this.albums=[a];
  ) {
  }

  ngOnInit() {
    this.albumService.getAlbumById(this.albumId)
    .subscribe((album: Album) => {
      this.model.album = album;
    });
  }
}
