import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';
import { SongService } from '../../../services/song.service';

import { Album } from '../../../models/album.model';
import { User } from '../../../models/user.model';
import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

    private user: User;
    private playlists: Playlist[];

    private history: Map<number, Song> = new Map();
    @ViewChild(MatSort) sort: any;
    private historyTableData: MatTableDataSource<Song>;
    private historyPlayButtonsVisibility: Map<number, boolean> = new Map();

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private fileService: FileService,
        private favoritesService: FavoritesService,
        private songService: SongService,
        private userService: UserService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        // this.artistService.getArtistById(Number(url[url.length - 1]))
        this.userService.getUserByUsername(url[url.length - 1])
            .subscribe((user: User) => {
                this.user = user;

                // Get user history
                this.userService.getHistoryById(user.id)
                    .subscribe((songs: Song[]) => {
                        this.historyTableData = new MatTableDataSource(songs);

                        songs.forEach((song: Song) => {
                            this.history.set(song.id, song);
                            this.historyPlayButtonsVisibility.set(song.id, false);

                            // // Get song album info
                            // this.songService.getSongAlbumInfo(song.id)
                            //     .subscribe((album: Album) => {
                            //         this.history.get(song.id).album = album[0];
                            //         console.log(this.history.get(song.id).album.title);

                            //         this.historyAlbumInfoReturns -= 1;
                            //         if (this.historyAlbumInfoReturns === 0) {
                            //             this.historyTableData = new MatTableDataSource(songs);
                            //         }
                            //     });

                            song.duration = Math.floor(song.duration / 1000);
                            song.durationString = `${Math.floor(song.duration / 60)}:`;
                            const seconds = song.duration % 60;
                            song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
                        });

                        // Get song favorited status
                        this.favoritesService.getFavoriteSongs()
                        .subscribe((favoritedSongs: Song[]) => {
                            songs.forEach((song: Song) => {
                                this.history.get(song.id).isFavorited = false;
                                favoritedSongs.forEach((favoritedSong: Song) => {
                                    if (song.id === favoritedSong.id) {
                                        this.history.get(song.id).isFavorited = true;
                                    }
                                });
                            });
                        });
                    });

                    // Get user playlists

            });
    }

    public setUser = (user: User): void => {
        this.user = user;
        this.getUserImage(this.user.id);
        this.userService.getHistory();
    }

    public getUserImage(userId: number): void {
        this.user.profileImageUrl = this.fileService.getUserImageURLById(userId);
    }

    private playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    private addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
    }

    private historyFavoriteOrUnfavoriteSong(songId: number): void {
        if (this.history.get(songId).isFavorited) {
            this.favoritesService.removeFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.history.get(songId).isFavorited = !success;
                });
        } else {
            this.favoritesService.addFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.history.get(songId).isFavorited = success;
                });
        }
    }

    private historyGetFavoritedIcon(songId: number, isFavorited: boolean): Object {
        if (this.historyPlayButtonsVisibility.get(songId)) {
            return (isFavorited) ? 'remove' : 'add';
        } else {
            return (isFavorited) ? 'favorite' : 'add';
        }
    }

    private historyShowPlay(songId: number): void {
        this.historyPlayButtonsVisibility.set(songId, true);
    }

    private historyHidePlay(songId: number): void {
        this.historyPlayButtonsVisibility.set(songId, false);
    }

    private historyGetPlayVisibility(songId: number): Object {
        return { 'visibility': this.historyPlayButtonsVisibility.get(songId) ? 'visible' : 'hidden' };
    }
}
