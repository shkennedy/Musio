import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SongService } from '../../services/song.service';
import { FavoritesService } from '../../services/favorites.service';

import { Song } from '../../models/song.model';
import { Artist } from '../../models/artist.model';

@Component({
  selector: 'app-song-view',
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {

  private songs: Song[];

  constructor(
    private router: Router,
    private songService: SongService,
    private favoritesService: FavoritesService
  ) {
    // let s=new Song();
    // s.title="soooong";
    // s.duration="3:45";
    // let a=new Artist();
    // a.name="guy";
    // s.artist=a;
    // this.songs=[s,s,s,s,s,s,s];
  }


  ngOnInit() {
    // this.favoritesService.addFavoriteSongById(4)
    // .subscribe((success: boolean) => {
    //     console.log('im passin out');
    // });

    this.favoritesService.getFavoriteSongs()
    .subscribe((songs: Song[]) => {
      this.songs = songs;
    });
  }

}
