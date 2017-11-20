import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SongService } from '../../services/song.service';
import { FavoritesService } from '../../services/favorites.service';

import { Song } from '../../models/song.model';

@Component({
  selector: 'app-song-view',
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {

  model: {
    songs: Song[]
  };

  constructor(
    private router: Router,
    private songService: SongService,
    private favoritesService: FavoritesService
  ) {
    // let s=new Song();
    // s.song_name="song one";
    // s.artist_name="singer boi";
    // s.album_name="greatest hit";
    // s.duration="2:34";
    // let s2=new Song();
    // s2.song_name="song one";
    // s2.artist_name="singer boi";
    // s2.album_name="greatest hit";
    // s2.duration="2:34";
    // let s3=new Song();
    // s3.song_name="song one";
    // s3.artist_name="singer boi";
    // s3.album_name="greatest hit";
    // s3.duration="2:34";
    // let s4=new Song();
    // s4.song_name="song one";
    // s4.artist_name="singer boi";
    // s4.album_name="greatest hit";
    // s4.duration="2:34";
    // this.songs=[s,s2,s3,s4];
  }

  ngOnInit() {
    this.favoritesService.getFavoriteSongs()
    .subscribe((songs: Song[]) => {
      this.model.songs = songs;
    });
  }

}
