import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort, MatTable } from '@angular/material';

import { SearchService, BrowseResponse, BrowseResponseTab } from '../../services/search.service';
import { FileService } from '../../services/file.service';

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
    private newReleasesSongTableData: MatTableDataSource<Song>;

    @ViewChild(MatSort) friendsFavoritesSort: MatSort;
    private friendsFavorites: BrowseResponseTab;
    private friendsFavoritesSongTableData: MatTableDataSource<Song>;

    @ViewChild(MatSort) discoverSort: MatSort;
    private discover: BrowseResponseTab;
    private discoverSongTableData: MatTableDataSource<Song>;

    @ViewChild(MatSort) popularSort: MatSort;
    private popular: BrowseResponseTab;
    private popularSongTableData: MatTableDataSource<Song>;

    private errorMessage: string;

    constructor(
        private router: Router,
        private fileService: FileService,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        this.searchService.getBrowse()
            .subscribe(
            (browseData: BrowseResponse) => {
                this.newReleases = browseData.newReleases;
                this.friendsFavorites = browseData.friendsFavorites;
                this.discover = browseData.discover;
                this.popular = browseData.popular;

                this.initBrowseResponseTab(this.newReleases);
                this.initBrowseResponseTab(this.friendsFavorites);
                this.initBrowseResponseTab(this.discover);
                this.initBrowseResponseTab(this.popular);
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            }
            );
    }

    private initBrowseResponseTab(browseData: BrowseResponseTab): void {
        browseData.songs.forEach((song: Song) => {
            song.duration = Math.floor(song.duration / 1000);
            song.durationString =
                `${Math.floor(song.duration / 60)}:`;
            const seconds = song.duration % 60;
            song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
        });

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
