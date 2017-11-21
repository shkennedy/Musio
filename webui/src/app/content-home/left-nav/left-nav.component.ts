import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

import { PlaylistService } from '../../services/playlist.service';

import { Playlist } from '../../models/playlist.model';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

  public playlists: Playlist[];

  // pass the playlists in this constructor
  constructor(
    private router: Router,
    private playlistService: PlaylistService
  ) {
    // sample data
    // let p=new Playlist();
    // p.name="playlist1";
    // p.id=1;
    // this.playlists=[p];
  }

  ngOnInit() {
  }
}
