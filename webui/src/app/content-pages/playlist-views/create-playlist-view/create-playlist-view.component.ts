import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

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
    private isPrivate: boolean;
    private playlistImage: any;
    private playlistImageName: string;

    private isLoaded = false;
    private hasSongs = false;
    @ViewChild(MatSort) sort: MatSort;
    private songTableManager: SongTableManager;

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
        private songService: SongService
    ) { }

    ngOnInit() {
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
                                    this.songTableManager.setSongs(songs);
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
                        this.songTableManager.setSort(this.sort);
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
}
