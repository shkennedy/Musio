import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort, MatTable } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { FileService } from '../../services/file.service';
import { FavoritesService } from '../../services/favorites.service';
import { PlaylistService } from '../../services/playlist.service';
import { SearchService, BrowseResponse, BrowseResponseTab } from '../../services/search.service';

import { SongTableManager } from '../../shared/songTableManager.module';

import { Song } from '../../models/song.model';
import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { Genre } from '../../models/genre.model';
import { Playlist } from '../../models/playlist.model';
import { Instrument } from '../../models/instrument.model';

@Component({
    selector: 'app-browse',
    templateUrl: './browse.component.html',
    styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {

    @ViewChild(MatSort) newReleasesSort: MatSort;
    private newReleases: BrowseResponseTab;
    private newReleasesSongTableInfo: SongTableManager;

    private hasFriendsContent: boolean;
    @ViewChild(MatSort) friendsFavoritesSort: MatSort;
    private friendsFavorites: BrowseResponseTab;
    private friendsFavoritesSongTableInfo: SongTableManager;

    @ViewChild(MatSort) discoverSort: MatSort;
    private discover: BrowseResponseTab;
    private discoverSongTableInfo: SongTableManager;

    @ViewChild('popularSort') popularSort: MatSort;
    private popular: BrowseResponseTab;
    private popularSongTableManager: SongTableManager;

    private errorMessage: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        this.searchService.getBrowse()
            .subscribe(
            (browseData: BrowseResponse) => {
                this.newReleases = browseData.newReleases;
                this.newReleasesSongTableInfo =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.initBrowseResponseTab(this.newReleases, this.newReleasesSongTableInfo, this.newReleasesSort);

                this.friendsFavorites = browseData.friendsFavorites;
                this.friendsFavoritesSongTableInfo =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.initBrowseResponseTab(this.friendsFavorites, this.friendsFavoritesSongTableInfo, this.friendsFavoritesSort);

                this.discover = browseData.discover;
                this.discoverSongTableInfo =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.initBrowseResponseTab(this.discover, this.discoverSongTableInfo, this.discoverSort);

                this.popular = browseData.popular;
                for (let popularityRank = 1; popularityRank <= this.popular.songs.length; popularityRank += 1) {
                    this.popular.songs[popularityRank - 1].trackNumber = popularityRank;
                }
                this.popularSongTableManager =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.initBrowseResponseTab(this.popular, this.popularSongTableManager, this.popularSort);
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            });
    }

    private initBrowseResponseTab(browseData: BrowseResponseTab,
        tableManager: SongTableManager, sort: MatSort): void {
        browseData.songs.forEach((song: Song) => {
            song.duration = Math.floor(song.duration / 1000);
            song.durationString =
                `${Math.floor(song.duration / 60)}:`;
            const seconds = song.duration % 60;
            song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
        });
        tableManager.setSongs(browseData.songs);
        setTimeout(() => {
            tableManager.setSort(sort);
        }, 2000);

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
}
