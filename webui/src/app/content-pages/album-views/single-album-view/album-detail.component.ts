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
  private fake:boolean = false;
  private isFavorited:boolean;
  private album: Album;

  constructor(
    private router: Router,
    private albumService: AlbumService,
    private favoritesService: FavoritesService
  ) { }

  ngOnInit() {
    // let album=new Album();
    // let a=new Artist();
    // a.name="Drake";
    // album.artists=[a];
    // album.title="The Black Album";
    // let s=new Song();
    // s.title="song1";
    // s.duration="2:22";
    // album.songs=[s,s,s,s,s,s,s,s,s];
    // this.album=album;
    let url:string[]=this.router.url.split("/");
    this.albumService.getAlbumById(Number(url[url.length-1]))
    .subscribe((album: Album) => {
      this.album = album;
    });

    this.favoritesService.getFavoriteAlbums()
     .subscribe(
     (albums: Album[]) => {
       if (albums) {
         this.isFavorited = albums.includes(this.album);
       }
     },
     (error: any) => {
       console.log(error);
     });
 }


    favoriteAlbum() {
      console.log("peanut");
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
   console.log("and");
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
