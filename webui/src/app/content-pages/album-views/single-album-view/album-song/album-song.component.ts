import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { SongService } from '../../../../services/song.service';

import { Song } from '../../../../models/song.model';

@Component({
  selector: 'album-song',
  templateUrl: './album-song.component.html',
  styleUrls: ['./album-song.component.css']
})
export class AlbumSongComponent implements OnInit {

  @Input() song: Song;
  @Input() track_number: number;

  constructor(
    private router: Router,
    private favoritesSevice: FavoritesService
  ) { }

  ngOnInit() {
  }
}
