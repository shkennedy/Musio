import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';
import { PlaylistService } from '../../../services/playlist.service';

import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
  selector: 'app-playlist-view',
  templateUrl: './playlist-view.component.html',
  styleUrls: ['./playlist-view.component.css']
})
export class PlaylistsViewComponent implements OnInit {

  model: {
    playlists: Playlist[]
  };

  constructor(
    private router: Router,
    private favoritesService: FavoritesService,
    private playlistService: PlaylistService
  ) {}

  ngOnInit() {
    this.favoritesService.getFavoritePlaylists()
    .subscribe((playlists: Playlist[]) => {
      this.model.playlists = playlists;
    });
  }
}
