import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort, MatTable } from '@angular/material';
import { Observable } from 'rxjs/Observable';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { SearchService, BrowseResponse, BrowseResponseTab } from '../../services/search.service';
import { FileService } from '../../services/file.service';
import { FavoritesService } from '../../services/favorites.service';

import { Song } from '../../models/song.model';
import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { Genre } from '../../models/genre.model';
import { Playlist } from '../../models/playlist.model';
import { Instrument } from '../../models/instrument.model';

class SongTableInfo {
    songs: Map<number, Song>;
    tableData: MatTableDataSource<Song>;
    playButtonsVisibility: Map<number, boolean>;

    constructor(
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
    ) {
        this.songs = new Map();
        this.tableData = new MatTableDataSource();
        this.playButtonsVisibility = new Map();
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
}

@Component({
    selector: 'app-browse',
    templateUrl: './browse.component.html',
    styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {

    @ViewChild(MatSort) newReleasesSort: MatSort;
    private newReleases: BrowseResponseTab;
    private newReleasesSongTableInfo: SongTableInfo;

    @ViewChild(MatSort) friendsFavoritesSort: MatSort;
    private friendsFavorites: BrowseResponseTab;
    private friendsFavoritesSongTableInfo: SongTableInfo;

    @ViewChild(MatSort) discoverSort: MatSort;
    private discover: BrowseResponseTab;
    private discoverSongTableInfo: SongTableInfo;

    @ViewChild('popularSort') popularSort: MatSort;
    private popular: BrowseResponseTab;
    private popularSongTableInfo: SongTableInfo;

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        this.searchService.getBrowse()
            .subscribe(
            (browseData: BrowseResponse) => {
                this.newReleases = browseData.newReleases;
                this.newReleasesSongTableInfo = new SongTableInfo(this.audioPlayerProxyService, this.favoritesService);
                this.initBrowseResponseTab(this.newReleases, this.newReleasesSongTableInfo, this.newReleasesSort);

                this.friendsFavorites = browseData.friendsFavorites;
                this.friendsFavoritesSongTableInfo = new SongTableInfo(this.audioPlayerProxyService, this.favoritesService);
                this.initBrowseResponseTab(this.friendsFavorites, this.friendsFavoritesSongTableInfo, this.friendsFavoritesSort);

                this.discover = browseData.discover;
                this.discoverSongTableInfo = new SongTableInfo(this.audioPlayerProxyService, this.favoritesService);
                this.initBrowseResponseTab(this.discover, this.discoverSongTableInfo, this.discoverSort);

                this.popular = browseData.popular;
                for (let popularityRank = 1; popularityRank <= this.popular.songs.length; popularityRank += 1) {
                    this.popular.songs[popularityRank - 1].trackNumber = popularityRank;
                }
                this.popularSongTableInfo = new SongTableInfo(this.audioPlayerProxyService, this.favoritesService);
                this.initBrowseResponseTab(this.popular, this.popularSongTableInfo, this.popularSort);
                console.log(this.popularSongTableInfo.tableData.data);
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            });
    }

    private initBrowseResponseTab(browseData: BrowseResponseTab,
        tableInfo: SongTableInfo, sort: MatSort): void {
        browseData.songs.forEach((song: Song) => {
            song.duration = Math.floor(song.duration / 1000);
            song.durationString =
                `${Math.floor(song.duration / 60)}:`;
            const seconds = song.duration % 60;
            song.durationString += (seconds < 10) ? `0${seconds}` : seconds;

            tableInfo.songs.set(song.id, song);
            tableInfo.playButtonsVisibility.set(song.id, false);
        });
        tableInfo.tableData.data = browseData.songs;
        setTimeout(() => {
            tableInfo.tableData.sort = sort;
        }, 1000);

        browseData.albums.forEach((album: Album) => {
            album.albumArtUrl = this.fileService.getAlbumImageURLByIdAndSize(album.id, true);
        });

        browseData.artists.forEach((artist: Artist) => {
            artist.artistImageUrl = this.fileService.getArtistImageURLByIdAndSize(artist.id, true);
        });

        // browseData.playlists.forEach((playlist: Playlist) => {
        //     playlist.playlistImageUrl = this.fileService.getPlaylistImageURLById(playlist.id);
        // });
    }

    private playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    private addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
    }
}
