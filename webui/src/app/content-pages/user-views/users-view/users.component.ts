import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';
import { PlaylistService } from '../../../services/playlist.service';
import { UserService } from '../../../services/user.service';
import { SongService } from '../../../services/song.service';

import { SongTableManager } from '../../../shared/songTableManager.module';

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
    private isSelf: true;

    private hasHistory = false;
    private historySongTableManager: SongTableManager;
    @ViewChild(MatSort) sort: any;

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private fileService: FileService,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private userService: UserService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        // this.artistService.getArtistById(Number(url[url.length - 1]))
        this.userService.getUserByUsername(url[url.length - 1])
            .subscribe((user: User) => {
                this.user = user;
                // Set user profile image to default url if none exist----------------------------------------

                // Get user history
                this.userService.getHistoryById(user.id)
                    .subscribe((songs: Song[]) => {
                        if (songs.length > 0) {
                            this.hasHistory = true;
                        }

                        this.historySongTableManager =
                            new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                        this.historySongTableManager.setSongs(songs);

                        songs.forEach((song: Song) => {
                            song.duration = Math.floor(song.duration / 1000);
                            song.durationString = `${Math.floor(song.duration / 60)}:`;
                            const seconds = song.duration % 60;
                            song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
                        });

                        // Get song favorited status
                        this.favoritesService.getFavoriteSongs()
                        .subscribe((favoritedSongs: Song[]) => {
                            songs.forEach((song: Song) => {
                                song.isFavorited = false;
                                favoritedSongs.forEach((favoritedSong: Song) => {
                                    if (song.id === favoritedSong.id) {
                                        song.isFavorited = true;
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
}
