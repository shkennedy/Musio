import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';

import { Album } from '../../../models/album.model';

@Component({
  selector: 'albums-view',
  templateUrl: './albums-view.component.html',
  styleUrls: ['./albums-view.component.css'],
  providers: [FavoritesService]
})
export class AlbumsViewComponent implements OnInit {

  private albums: Album[];

  constructor(
    private router: Router,
    private favoritesSevice: FavoritesService
  ) {
    // const a = new Album();
    // a.album_name = 'cool album';
    // a.artist_name = 'another bingus hinguson';
    // this.albums = [a];
  }

  ngOnInit() {
    this.favoritesSevice.getFavoriteAlbums()
    .subscribe((albums: Album[]) => {
      this.albums = albums;
    });
  }
}
