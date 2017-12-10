import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

    private newReleases: BrowseResponseTab;
    private friendsFavorites: BrowseResponseTab;
    private discover: BrowseResponseTab;
    private popular: BrowseResponseTab;

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
