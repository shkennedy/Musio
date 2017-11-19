import { Component, OnInit } from '@angular/core'
import { Playlist } from '../../models/playlist.model'
@Component({
  selector: 'app-playlist-view',
  templateUrl: './playlist-view.component.html',
  styleUrls: ['./playlist-view.component.css']
})
export class PlaylistViewComponent implements OnInit {

  constructor(private playlists:Playlist[]) {}

  ngOnInit() {
  }

}
