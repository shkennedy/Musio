import { Component, OnInit } from '@angular/core';
import { Song } from '../../models/song.model'
import { Album } from '../../models/album.model'
import { Artist } from '../../models/artist.model'
import { Playlist } from '../../models/playlist.model'
@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {
  public albums:Album[];
  public artists:Artist[];
  public playlists:Playlist[];
  public songs:Song[];
  constructor() {}

  ngOnInit() {
  }

}
