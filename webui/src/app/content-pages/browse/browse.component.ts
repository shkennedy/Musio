import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort, MatTable } from '@angular/material';

import { SearchService, BrowseResponseTab } from '../../services/search.service';
import { BrowseResponse } from '../../services/search.service';

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
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            }
        );
    }
}
