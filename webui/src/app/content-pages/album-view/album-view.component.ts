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

  @Input() albumId: number;

  model: {
    album: Album
  };

  constructor(
    private router: Router,
    private albumService: AlbumService,
    private favoritesService: FavoritesService
  ) {
    /*this.albums=
    [
      {album_id:1,
      album_name:"ALBUM1",
      album_art:null,
      album_duration:53,
      artist_id:33,
      artist_name:"MR ARTIST",
      num_songs:12,
      songs:
      [
        {song_id:1,
        song_name: "Song1",
        audio:null,
        lyrics:null,
        duration:"4:33",
        genre:[],
        artist_id:1,
        artist_name:"MR ARTIST",
        album_id:1,
        album_name:"ALBUM1"
        }
      ],
      genre:[],
      release_date:new Date()
      }
    ]*/
    // this.albums=[]
  }

  ngOnInit() {
    this.albumService.getAlbumById(this.albumId)
    .subscribe((album: Album) => {
      this.model.album = album;
    });
  }
}
