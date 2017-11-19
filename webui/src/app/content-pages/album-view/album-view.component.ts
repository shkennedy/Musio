import { Component, OnInit } from '@angular/core'
import { Album } from '../../models/album.model'
@Component({
  selector: 'app-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.css']
})
export class AlbumViewComponent implements OnInit {
  public albums:Album[]
  constructor() {
    /*this.albums=
    [
      {album_id:1,
      album_name:"ALBUM1",
      album_art:null,
      album_duration:53,
      artist_id:33,
      artist_name:"MR ARTIST",
      num_songs:12,
      songs:
      [
        {song_id:1,
        song_name: "Song1",
        audio:null,
        lyrics:null,
        duration:"4:33",
        genre:[],
        artist_id:1,
        artist_name:"MR ARTIST",
        album_id:1,
        album_name:"ALBUM1"
        }
      ],
      genre:[],
      release_date:new Date()
      }
    ]*/
    this.albums=[]
  }
  ngOnInit() {
  }

}
