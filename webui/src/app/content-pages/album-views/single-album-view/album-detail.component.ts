import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Song } from '../../../models/song.model';
import { Album } from '../../../models/album.model';
import { Artist } from '../../../models/artist.model';

@Component({
    selector: 'album-detail',
    templateUrl: './album-detail.component.html',
    styleUrls: ['./album-detail.component.css']
})
export class AlbumDetailComponent implements OnInit {

    //@Input() albumId: number;
    private imageExists: boolean = false;
    private isFavorited: boolean;
    private album: Album;

    private errorMessage: string = '';

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private favoritesService: FavoritesService
    ) { }

    ngOnInit() {
        let url: string[] = this.router.url.split("/");
        this.albumService.getAlbumById(Number(url[url.length - 1]))
            .subscribe(
                (album: Album) => {
                    this.album = album;
                },
                (error: any) => {
                    this.errorMessage = error;
                }
        );

        this.favoritesService.getFavoriteAlbums()
        .subscribe(
            (albums: Album[]) => {
                if (albums) {
                    for (let i = 0; i < albums.length; ++i) {
                        if (albums[i].id == this.album.id) {
                            this.isFavorited = true;
                            console.log('album-detail.component found album is favorited');
                        }
                    }
                }
            },
            (error: any) => {
                console.log(error.toString());
            }
        );
    }


    favoriteAlbum() {
        this.favoritesService.addFavoriteAlbumById(this.album.id)
            .subscribe(
                (success: boolean) => {
                    this.isFavorited = true;
                },
                (error: any) => {
                    console.log(error.toString());
                }
            );
    }

    unfavoriteAlbum() {
        this.favoritesService.removeFavoriteAlbumById(this.album.id)
            .subscribe(
                (success: boolean) => {
                    this.isFavorited = false;
                },
                (error: any) => {
                    console.log(error.toString());
                }
            );
    }
}
