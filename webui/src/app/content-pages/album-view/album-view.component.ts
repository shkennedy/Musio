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
    let a=new Album();
    a.album_name="cool album";
    a.artist_name="another bingus hinguson";
    this.albums=[a];
  }
  ngOnInit() {
  }

}
