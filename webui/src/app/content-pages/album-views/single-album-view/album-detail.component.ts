import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';

import { Song } from '../../../models/song.model';
import { Album } from '../../../models/album.model';
import { Artist } from '../../../models/artist.model';

@Component({
    selector: 'app-album-detail',
    templateUrl: './album-detail.component.html',
    styleUrls: ['./album-detail.component.css'],
    providers: [AlbumService, FavoritesService, FileService]
})
export class AlbumDetailComponent implements OnInit {

    private isFavorited: boolean;
    private album: Album;
    private titleSort = false;
    private ascendingOrder = true;

    private errorMessage = '';

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        this.albumService.getAlbumById(Number(url[url.length - 1]))
            .subscribe(
            (album: Album) => {
                this.album = album;

                // Check if favorited
                this.favoritesService.getFavoriteAlbums()
                    .subscribe(
                    (albums: Album[]) => {
                        if (albums) {
                            for (let i = 0; i < albums.length; ++i) {
                                if (albums[i].id === this.album.id) {
                                    this.isFavorited = true;
                                    console.log('album-detail.component found album is favorited');
                                }
                            }
                        }
                    },
                    (error: any) => {
                        this.errorMessage = error;
                    });
            },
            (error: any) => {
                this.errorMessage = error;
            });
    }

    private favoriteAlbum(): void {
        this.favoritesService.addFavoriteAlbumById(this.album.id)
            .subscribe(
            (success: boolean) => {
                this.isFavorited = true;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private unfavoriteAlbum(): void {
        this.favoritesService.removeFavoriteAlbumById(this.album.id)
            .subscribe(
            (success: boolean) => {
                this.isFavorited = false;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private sort(type: string): void {
        if (type === 'title') {
            if (this.titleSort) {
                this.ascendingOrder = !this.ascendingOrder;
            } else {
                this.titleSort = true;
            }
            this.albumService.sortAlbumBySongTitle(this.album, this.ascendingOrder);
        } else {
            if (!this.titleSort) {
                this.ascendingOrder = !this.ascendingOrder;
            } else {
                this.titleSort = false;
            }
            this.albumService.sortAlbumByTrack(this.album, this.ascendingOrder);
        }
    }

    private playAlbum(): void {
        this.audioPlayerProxyService.playAlbum(this.album.id);
    }

    private addAlbumToQueue(): void {
        this.audioPlayerProxyService.addAlbumToQueue(this.album.id);
    }

    private playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    private addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
    }
}
