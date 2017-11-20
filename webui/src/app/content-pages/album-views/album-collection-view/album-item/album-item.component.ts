import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { AlbumService } from '../../../../services/album.service';
import { Album } from '../../../../models/album.model';

@Component({
  selector: 'album-item',
  templateUrl: './album-item.component.html',
  styleUrls: ['./album-item.component.css'],
  providers: [AlbumService, FavoritesService]
})
export class AlbumItemComponent implements OnInit {

  @Input() album: Album;

  constructor(
    private router: Router,
    private favoritesService: FavoritesService,
    private albumService: AlbumService
  ) { }

  ngOnInit() {
    // this.albumService.getAlbumById(this.albumId)
    // .subscribe((album: Album) => {
    //   this.model.album = album;
    // });
  }
}
