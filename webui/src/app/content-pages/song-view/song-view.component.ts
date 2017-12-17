import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { SongService } from '../../services/song.service';
import { FavoritesService } from '../../services/favorites.service';

import { Song } from '../../models/song.model';
import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';

@Component({
    selector: 'app-song-view',
    templateUrl: './song-view.component.html',
    styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {

    private songs: Map<number, Song>;
    @ViewChild(MatSort) sort: any;
    private songTableData: MatTableDataSource<Song>;
    private playButtonsVisibility: Map<number, boolean> = new Map();

    private albumInfoReturns: number;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private songService: SongService,
        private favoritesService: FavoritesService
    ) { }


    ngOnInit() {
        this.favoritesService.getFavoriteSongs()
            .subscribe(
            (songs: Song[]) => {
                if (songs.length !== 0) {
                    this.songs = new Map();
                    this.albumInfoReturns = songs.length;
                    songs.forEach((song: Song) => {
                        this.songService.getSongAlbumInfo(song.id)
                            .subscribe((album: Album) => {
                                this.songs.get(song.id).album = album[0];

                                this.albumInfoReturns -= 1;
                                if (this.albumInfoReturns === 0) {
                                    this.songTableData = new MatTableDataSource(songs);
                                }
                            });

                        this.songs.set(song.id, song);
                        this.playButtonsVisibility.set(song.id, false);

                        song.isFavorited = true;
                        song.duration = Math.floor(song.duration / 1000);
                        song.durationString =
                            `${Math.floor(song.duration / 60)}:`;
                        const seconds = song.duration % 60;
                        song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
                    });

                    setTimeout(() => {
                        this.songTableData.sort = this.sort;
                    }, 3000);
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    private addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
    }

    private favoriteOrUnfavoriteSong(songId: number): void {
        if (this.songs.get(songId).isFavorited) {
            this.favoritesService.removeFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.songs.get(songId).isFavorited = !success;
                });
        } else {
            this.favoritesService.addFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.songs.get(songId).isFavorited = success;
                });
        }
    }

    private getFavoritedIcon(songId: number, isFavorited: boolean): Object {
        if (this.playButtonsVisibility.get(songId)) {
            return (isFavorited) ? 'remove' : 'add';
        } else {
            return (isFavorited) ? 'favorite' : 'add';
        }
    }

    private showPlay(songId: number): void {
        this.playButtonsVisibility.set(songId, true);
    }

    private hidePlay(songId: number): void {
        this.playButtonsVisibility.set(songId, false);
    }

    private getPlayVisibility(songId: number): Object {
        return { 'visibility': this.playButtonsVisibility.get(songId) ? 'visible' : 'hidden' };
    }
}
