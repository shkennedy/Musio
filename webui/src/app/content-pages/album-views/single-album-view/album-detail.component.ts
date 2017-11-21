import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Song } from '../../../models/song.model';
import { Album } from '../../../models/album.model';

@Component({
  selector: 'album-detail',
  templateUrl: './album-detail.component.html',
  styleUrls: ['./album-detail.component.css']
})
export class AlbumDetailComponent implements OnInit {

  //@Input() albumId: number;
  private fake:boolean=true;
  private album: Album;

  constructor(
    private router: Router,
    private albumService: AlbumService,
    private favoritesService: FavoritesService
  ) { }

  ngOnInit() {
    // this.albumService.getAlbumById(this.albumId)
    // .subscribe((album: Album) => {
    //   this.album = album;
    // });
  }
}
