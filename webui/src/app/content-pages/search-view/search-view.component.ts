import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SearchService, SearchResponse } from '../../services/search.service';
import { BrowseResponse } from '../../services/search.service';

import { Song } from '../../models/song.model';
import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { Genre } from '../../models/genre.model';
import { Playlist } from '../../models/playlist.model';
import { Instrument } from '../../models/instrument.model';
import { User } from '../../models/user.model';

@Component({
    selector: 'app-search',
    templateUrl: './search-view.component.html',
    styleUrls: ['./search-view.component.css']
})
export class SearchComponent implements OnInit {

    @Input() searchQuery: string;

    private albums: Album[];
    private artists: Artist[];
    private genres: Genre[];
    private instruments: Instrument[];
    private playlists: Playlist[];
    private songs: Song[];
    private users: User[];

    private errorMessage: string;

    constructor(
        private router: Router,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        console.log(this.searchQuery);
        this.searchService.search(this.searchQuery)
        .subscribe(
            (searchData: SearchResponse) => {
                this.albums = searchData.albums;
                this.artists = searchData.artists;
                this.instruments = searchData.instruments;
                this.genres = searchData.genres;
                this.playlists = searchData.playlists;
                this.songs = searchData.songs;
                this.users = searchData.users;
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            }
        );
    }
}
