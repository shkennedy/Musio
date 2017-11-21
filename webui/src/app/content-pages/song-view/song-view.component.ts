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

  private songs:Song[];

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
  }

  ngOnInit() {
    this.favoritesService.getFavoriteSongs()
    .subscribe((songs: Song[]) => {
      this.songs = songs;
    });
  }

}
