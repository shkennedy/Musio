import { Component, OnInit, Input } from '@angular/core';
import { Song } from '../../../models/song.model'
@Component({
  selector: 'album-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css']
})
export class AlbumItemComponent implements OnInit {
  @Input() song: Song;
  @Input() track_number: number;
  constructor() { }

  ngOnInit() {
  }

}
