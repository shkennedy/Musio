import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { PlaylistService } from '../../services/playlist.service';

import { Playlist } from '../../models/playlist.model';
import { Song } from '../../models/song.model';

@Component({
  selector: 'app-playlist-view',
  templateUrl: './playlist-view.component.html',
  styleUrls: ['./playlist-view.component.css']
})
export class PlaylistViewComponent implements OnInit {

  @Input() playlistId: number;

  model: {
    playlist: Playlist
  };

  constructor(
    private router: Router,
    private playlistService: PlaylistService
  ) {}

  ngOnInit() {
    this.playlistService.getPlaylistById(this.playlistId)
    .subscribe((playlist: Playlist) => {
      this.model.playlist = playlist;
    });
  }
}
