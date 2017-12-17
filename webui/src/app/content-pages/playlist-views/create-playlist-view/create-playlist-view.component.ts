import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import { FavoritesService } from '../../../services/favorites.service';
import { PlaylistService } from '../../../services/playlist.service';
import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';

import { SongTableManager } from '../../../shared/songTableManager.module';

import { Album } from '../../../models/album.model';
import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';
import { SongService } from '../../../services/song.service';

@Component({
    selector: 'app-create-playlist-view',
    templateUrl: './create-playlist-view.component.html',
    styleUrls: ['./create-playlist-view.component.css']
})
export class CreatePlaylistViewComponent implements OnInit {

    private playlists: Playlist[];

    private playlistName: string;
    private isPrivate = false;
    private isCollaborative = false;
    private playlistImage: any;
    private playlistImageName: string;

    private isLoaded = false;
    private allowIsCollaborative = true;
    private hasSongs = false;
    @ViewChild(MatSort) sort: MatSort;
    private notAddedTableManager: SongTableManager;
    private addedTableManager: SongTableManager;
    private playlistIdentityFormGroup: FormGroup;

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private formBuilder: FormBuilder,
    ) { }

    ngOnInit() {
        this.playlistIdentityFormGroup = this.formBuilder.group({
            playlistIdentityControl: ['', Validators.required]
        });

        this.notAddedTableManager =
            new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
        this.addedTableManager =
            new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);

        this.favoritesService.getFavoritePlaylists()
            .subscribe((playlists: Playlist[]) => {
                this.playlists = playlists;
            },
            (error: any) => {
                this.errorMessage = error;
            });

        this.favoritesService.getFavoriteSongs()
            .subscribe(
            (songs: Song[]) => {
                if (songs.length !== 0) {
                    this.hasSongs = true;
                    let albumInfoReturns = songs.length;
                    songs.forEach((song: Song) => {
                        this.songService.getSongAlbumInfo(song.id)
                            .subscribe((album: Album) => {
                                song.album = album[0];

                                albumInfoReturns -= 1;
                                if (albumInfoReturns === 0) {
                                    this.notAddedTableManager.setSongs(songs);
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

                    setTimeout(() => {
                        this.notAddedTableManager.setSort(this.sort);
                        this.addedTableManager.setSort(this.sort);
                    }, 3000);
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private playlistImageSelected(playlistImage: any): void {
        const reader = new FileReader();
        if (playlistImage.target.files && playlistImage.target.files[0]) {
            reader.readAsDataURL(playlistImage.target.files[0]);
            reader.onload = ((e) => {
                const data = e.target['result'].split(',');
                this.playlistImage = data[1];
                this.playlistImageName = playlistImage.target.files[0].name;
            });
        }
    }

    private setIsPrivate(isPrivate: boolean): void {
        this.isPrivate = isPrivate;
        if (this.isPrivate) {
            this.isCollaborative = false;
            this.allowIsCollaborative = false;
        } else {
            this.allowIsCollaborative = true;
        }
    }

    private setIsCollaborative(isCollaborative: boolean): void {
        if (isCollaborative && this.allowIsCollaborative) {
            this.isCollaborative = isCollaborative;
        } else {
            this.isCollaborative = false;
        }
    }

    private addSongToPlaylist(song: Song): void {
        this.notAddedTableManager.removeSong(song);
        this.addedTableManager.addSong(song);
    }

    private removeSongFromPlaylist(song: Song): void {
        this.addedTableManager.removeSong(song);
        this.notAddedTableManager.addSong(song);
    }

    private createPlaylist(): void {
        this.playlistService.createPlaylist()
        .subscribe((newPlaylist: Playlist) => {
            console.log(newPlaylist);
            newPlaylist.name = this.playlistName;
            newPlaylist.isPrivate = this.isPrivate;
            newPlaylist.songs = this.addedTableManager.getSongs();
            newPlaylist.isCollaborative = this.isCollaborative;
            this.router.navigate(['/playlists']);
        },
        (error: any) => {
            console.log(error);
        });
    }
}
