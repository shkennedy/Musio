import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SearchService } from '../../services/search.service';
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
    
    private albums: Album[];
    private artists: Artist[];
    private genres: Genre[];
    private instruments: Instrument[];
    private playlists: Playlist[];
    private songs: Song[];

    private errorMessage: string;
    
    constructor(
        private router: Router,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        this.searchService.getBrowse()
        .subscribe(
            (browseData: BrowseResponse) => {
                this.albums = browseData.albums;
                this.artists = browseData.artists;
                this.instruments = browseData.instruments
                this.genres = browseData.genres;
                this.playlists = browseData.playlists;
                this.songs = browseData.songs;
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            }
        );
    }
}
