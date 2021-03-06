import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { AlbumService } from '../../../services/album.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';

import { SongTableManager } from '../../../shared/songTableManager.module';

import { Song } from '../../../models/song.model';
import { Album } from '../../../models/album.model';
import { Artist } from '../../../models/artist.model';
import { timeout } from 'rxjs/operators/timeout';
import { PlaylistService } from '../../../services/playlist.service';

@Component({
    selector: 'app-album-detail',
    templateUrl: './album-detail.component.html',
    styleUrls: ['./album-detail.component.css'],
    providers: [AlbumService, FavoritesService, FileService]
})
export class AlbumDetailComponent implements OnInit {

    private album: Album;
    private isFavorited: boolean;
    private songs: Map<number, Song> = new Map();
    private titleSort = false;
    private ascendingOrder = true;

    @ViewChild(MatSort) sort: any;
    private albumSongTableManager: SongTableManager;

    private errorMessage = '';

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        this.albumService.getAlbumById(Number(url[url.length - 1]))
            .subscribe(
            (album: Album) => {
                this.album = album;
                this.albumSongTableManager =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);

                this.albumSongTableManager.setSongs(this.album.songs);
                setTimeout(() => {
                    this.albumSongTableManager.setSort(this.sort);
                }, 2000);

                // Check if favorited
                this.favoritesService.getFavoriteAlbums()
                    .subscribe(
                    (albums: Album[]) => {
                        if (albums) {
                            for (let i = 0; i < albums.length; ++i) {
                                if (albums[i].id === this.album.id) {
                                    this.isFavorited = true;
                                }
                            }
                        }
                    },
                    (error: any) => {
                        this.errorMessage = error;
                    });

                this.favoritesService.getFavoriteSongs()
                    .subscribe(
                    (songs: Song[]) => {
                        console.log(songs);
                        this.songs.forEach((song: Song) => {
                            song.isFavorited = false;
                            songs.forEach((favoritedSong: Song) => {
                                if (favoritedSong.id === song.id) {
                                    song.isFavorited = true;
                                }
                            });
                        });
                    },
                    (error: any) => {
                        console.log('error fetching favorite songs');
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

    private playAlbum(): void {
        this.audioPlayerProxyService.playAlbum(this.album.id);
    }

    private addAlbumToQueue(): void {
        this.audioPlayerProxyService.addAlbumToQueue(this.album.id);
    }
}
