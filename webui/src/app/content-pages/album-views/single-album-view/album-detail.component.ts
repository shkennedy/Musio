import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Song } from '../../../models/song.model';
import { Album } from '../../../models/album.model';
import { Artist } from '../../../models/artist.model';

@Component({
  selector: 'album-detail',
  templateUrl: './album-detail.component.html',
  styleUrls: ['./album-detail.component.css']
})
export class AlbumDetailComponent implements OnInit {

  //@Input() albumId: number;
  private imageExists: boolean = false;
  private isFavorited: boolean = true;
  private album: Album;

  constructor(
    private router: Router,
    private albumService: AlbumService,
    private favoritesService: FavoritesService
  ) { }

  ngOnInit() {
    let url:string[]=this.router.url.split("/");
    this.albumService.getAlbumById(Number(url[url.length-1]))
    .subscribe((album: Album) => {
      this.album = album;
    });

    // this.favoritesService.getFavoriteAlbums()
    //  .subscribe(
    //  (albums: Album[]) => {
    //    console.log(albums);
    //    if (albums) {
    //      for (let i = 0; i < albums.length; ++i) {
    //        if (albums[i].id == this.album.id) {
    //          this.isFavorited = true;
    //        }
    //      }
    //     }
    //  },
    //  (error: any) => {
    //    console.log(error);
    //  });
 }


    favoriteAlbum() {
   this.favoritesService.addFavoriteAlbumById(this.album.id)
     .subscribe(
     (success: boolean) => {
       this.isFavorited = true;
       console.log("butter");
     },
     (error: any) => {
       console.log(error);
     }
     );
 }

 unfavoriteAlbum() {
   this.favoritesService.removeFavoriteAlbumById(this.album.id)
     .subscribe(
     (success: boolean) => {
       this.isFavorited = false;
       console.log("plumbus");
     },
     (error: any) => {
       console.log(error);
     }
     );
 }


}
