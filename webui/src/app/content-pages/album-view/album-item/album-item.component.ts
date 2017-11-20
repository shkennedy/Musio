import { Component, OnInit, Input } from '@angular/core';
import { Album } from '../../../models/album.model'
@Component({
  selector: 'album-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css']
})
export class AlbumItemComponent implements OnInit {
  @Input() album: Album;
  //remove this later
  private fake:boolean = false;
  constructor() { }

  ngOnInit() {
  }

}
