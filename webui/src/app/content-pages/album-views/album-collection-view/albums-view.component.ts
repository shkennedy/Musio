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

  private fake: boolean=false;
  private albums: Album[];

  constructor(
    private router: Router,
    private favoritesSevice: FavoritesService
  ) {
    // const a = new Album();
    // a.title = 'cool album';
    // this.albums = [a];
  }

  ngOnInit() {

    // this.favoritesSevice.addFavoriteAlbumById(1)
    // .subscribe((success: boolean) => {
    // });this.favoritesSevice.addFavoriteAlbumById(2)
    // .subscribe((success: boolean) => {
    // });this.favoritesSevice.addFavoriteAlbumById(3)
    // .subscribe((success: boolean) => {
    // });this.favoritesSevice.addFavoriteAlbumById(4)
    // .subscribe((success: boolean) => {
    // });this.favoritesSevice.addFavoriteAlbumById(5)
    // .subscribe((success: boolean) => {
    // });
    this.favoritesSevice.getFavoriteAlbums()
    .subscribe((albums: Album[]) => {
      this.albums = albums;
      // console.log(this.albums[0].title);
    });
  }
}
