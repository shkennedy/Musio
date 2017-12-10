import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';

import { Album } from '../../../models/album.model';

@Component({
    selector: 'app-albums-view',
    templateUrl: './albums-view.component.html',
    styleUrls: ['./albums-view.component.css'],
    providers: [AlbumService, FavoritesService, FileService]
})
export class AlbumsViewComponent implements OnInit {

    private albums: Album[];
    private titleSort = true;
    private ascendingOrder = true;

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private favoritesSevice: FavoritesService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        this.favoritesSevice.getFavoriteAlbums()
            .subscribe(
                (albums: Album[]) => {
                    if (albums.length !== 0) {
                        this.albums = albums;
                        albums.forEach((album: Album) => {
                            album.albumArtUrl =
                                this.fileService.getAlbumImageURLByIdAndSize(album.albumArtId, false);
                        });
                        this.sort();
                    }
                },
                (error: any) => {
                    console.log(error.toString());
                });
    }

    private sort(): void {
        if (this.titleSort) {
            this.albumService.sortAlbumsByTitle(this.albums, this.ascendingOrder);
        } else {
            this.albumService.sortAlbumsByReleaseDate(this.albums, this.ascendingOrder);
        }
    }
}
