import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { FileService } from '../../../services/file.service';
import { PlaylistService } from '../../../services/playlist.service';
import { UserService } from '../../../services/user.service';
import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../../services/favorites.service';
import { SongService } from '../../../services/song.service';
import { LeftBarProxyService } from '../../../services/leftBarProxy.service';

import { SongTableManager } from '../../../shared/songTableManager.module';

import { Album } from '../../../models/album.model';
import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';
import { User } from '../../../models/user.model';

@Component({
    selector: 'app-playlist-view',
    templateUrl: './playlist-view.component.html',
    styleUrls: ['./playlist-view.component.css']
})
export class PlaylistViewComponent implements OnInit {

    private playlist: Playlist;
    private isOwner: boolean;
    private isEditable: boolean;
    private isFavorited: boolean;
    private ascendingOrder: boolean;

    private hasSongs = false;
    private isLoaded = false;
    @ViewChild(MatSort) sort: any;
    private songTableManager: SongTableManager;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private userService: UserService,
        private leftBarProxyService: LeftBarProxyService
    ) { }

    ngOnInit() {
        this.songTableManager =
            new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);

        const url: string[] = this.router.url.split('/');
        this.playlistService.getPlaylistById(Number(url[url.length - 1]))
            .subscribe(
            (playlist: Playlist) => {
                this.playlist = playlist;
                this.isEditable = this.playlist.isCollaborative;
                this.userService.getUser(this.getIsOwner);

                if (playlist.songs.length !== 0) {
                    this.hasSongs = true;
                    let albumInfoReturns = playlist.songs.length;
                    playlist.songs.forEach((song: Song) => {
                        this.songService.getSongAlbumInfo(song.id)
                            .subscribe((album: Album) => {
                                song.album = album[0];

                                albumInfoReturns -= 1;
                                if (albumInfoReturns === 0) {
                                    this.songTableManager.setSongs(playlist.songs);
                                    setTimeout(() => {
                                        this.songTableManager.setSort(this.sort);
                                    }, 3000);
                                    this.isLoaded = true;
                                }
                            });

                        song.isFavorited = true;
                        song.duration = Math.floor(song.duration / 1000);
                        song.durationString =
                            `${Math.floor(song.duration / 60)}:`;
                        const seconds = song.duration % 60;
                        song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
                    });
                }
            },
            (error: any) => {
                console.log(error);
            });
    }

    private getIsOwner = (user: User): void => {
        this.isOwner = this.playlist.ownerId === user.id;
        if (this.isOwner) {
            this.isEditable = true;
        }
    }

    private renamePlaylist(): void {
        if (this.isEditable) {
            this.playlistService.updatePlaylist(this.playlist)
                .subscribe((playlist: Playlist) => {
                    console.log('changed name');
                });
        }
    }

    private followPlaylist(): void {
        this.favoritesService.addFavoritePlaylistById(this.playlist.id)
            .subscribe((success: boolean) => {
                if (success) {
                    this.isFavorited = true;
                    this.leftBarProxyService.refreshPlaylists();
                }
            });
    }

    private unfollowPlaylist(): void {
        this.favoritesService.removeFavoritePlaylistById(this.playlist.id)
            .subscribe((success: boolean) => {
                if (success) {
                    this.isFavorited = false;
                    this.leftBarProxyService.refreshPlaylists();
                }
            });
    }

    private removeSongFromPlaylist(song: Song): void {
        this.songTableManager.removeSong(song);
        this.playlistService.removeSong(this.playlist.id, song.id)
            .subscribe((playlist: Playlist) => {
            },
            (error: any) => {
                console.log(error);
            });
    }

    private deletePlaylist(): void {
        this.playlistService.deletePlaylist(this.playlist.id)
            .subscribe((success: boolean) => {
                console.log(`deleted playlist ${success}`);
                this.router.navigate(['/playlists']);
            });
    }
}
