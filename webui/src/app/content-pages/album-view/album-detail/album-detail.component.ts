import { Component, OnInit } from '@angular/core';
import { Album } from '../../../models/album.model'
@Component({
  selector: 'album-detail',
  templateUrl: './album-detail.component.html',
  styleUrls: ['./album-detail.component.css']
})
export class AlbumDetailComponent implements OnInit {
  private album:Album
  constructor() { }

  ngOnInit() {
  }

}
