import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FileService } from '../../services/file.service';
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

    private searchQuery: string;

    private albums: Album[];
    private artists: Artist[];
    private genres: Genre[];
    private instruments: Instrument[];
    private playlists: Playlist[];
    private songs: Song[];
    private users: User[];

    private errorMessage: string;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private fileService: FileService,
        private searchService: SearchService
    ) { }

    ngOnInit() {
        this.activatedRoute.params
            .subscribe((searchParams: any) => {
                this.searchQuery = searchParams['searchQuery'];
                this.search();
        });
    }

    private search(): void {
        console.log(this.searchQuery);
        this.searchService.search(this.searchQuery)
            .subscribe(
            (searchData: SearchResponse) => {
                this.albums = searchData.albums;
                if (this.albums) {
                    this.albums.forEach((album: Album) => {
                        album.albumArtUrl =
                            this.fileService.getAlbumImageURLByIdAndSize(album.id, false);
                    });
                }

                this.artists = searchData.artists;
                if (this.artists) {
                    this.artists.forEach((artist: Artist) => {
                        artist.artistImageUrl =
                            this.fileService.getArtistImageURLByIdAndSize(artist.id, false);
                    });
                }

                this.instruments = searchData.instruments;

                this.genres = searchData.genres;
                if (this.genres) {
                    this.genres.forEach((genre: Genre) => {
                        // genre.genreImageUrl = TODO
                        //     this.fileService.getGenreImageURLByIdAndSize(genre.genreImageId, false);
                    });
                }

                this.playlists = searchData.playlists;
                if (this.playlists) {
                    this.playlists.forEach((playlist: Playlist) => {
                        // playlist.playlistImageUrl = TODO
                        //     this.fileService.getPlaylistImageURLByIdAndSize(playlist.playlistImageId, false);
                    });
                }

                this.songs = searchData.songs;

                this.users = searchData.users;
                if (this.users) {
                    this.users.forEach((user: User) => {
                        // user.profileImageUrl =
                            // this.fileService.getAlbumImageURLByIdAndSize(user.id, false);
                    });
                }
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            });
    }
}
