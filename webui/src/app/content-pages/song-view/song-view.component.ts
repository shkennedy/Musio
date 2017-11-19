import { Component, OnInit } from '@angular/core';
import { Song } from '../../models/song.model'
@Component({
  selector: 'app-song-view',
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {
  constructor(private songs:Song[]) {}
  ngOnInit() {
  }

}
