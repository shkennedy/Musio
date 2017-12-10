import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { AlbumService } from '../../../../services/album.service';
import { Album } from '../../../../models/album.model';

@Component({
    selector: 'app-album-item',
    templateUrl: './album-item.component.html',
    styleUrls: ['./album-item.component.css'],
    providers: [AlbumService, FavoritesService]
})
export class AlbumItemComponent implements OnInit {

    @Input() albumId: number;

    public album: Album;
    private titleSort: boolean;
    private ascendingOrder: boolean;

    public errMsg: string;

    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private albumService: AlbumService
    ) { }

    ngOnInit() {
        this.albumService.getAlbumById(this.albumId)
            .subscribe(
                (album: Album) => {
                    this.album = album;
                },
                (error: any) => {
                    this.errMsg = error;
                }
            );
    }

    private sort(): void {
        // this.ascendingOrder = !this.ascendingOrder;
        if (this.titleSort) {
            this.albumService.sortAlbumBySongTitle(this.album, this.ascendingOrder);
        } else {
            this.albumService.sortAlbumByTrack(this.album, this.ascendingOrder);
        }
    }
}
