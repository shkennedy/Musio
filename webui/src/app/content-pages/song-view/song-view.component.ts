import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { SongService } from '../../services/song.service';
import { FavoritesService } from '../../services/favorites.service';
import { PlaylistService } from '../../services/playlist.service';

import { SongTableManager } from '../../shared/songTableManager.module';

import { Song } from '../../models/song.model';
import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';

@Component({
    selector: 'app-song-view',
    templateUrl: './song-view.component.html',
    styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {

    private hasSongs = false;
    @ViewChild(MatSort) sort: any;
    private songTableManager: SongTableManager;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private songService: SongService,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
    ) { }


    ngOnInit() {
        this.songTableManager =
            new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);

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
}
