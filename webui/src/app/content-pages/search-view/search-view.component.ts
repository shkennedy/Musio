import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../services/favorites.service';
import { FileService } from '../../services/file.service';
import { SearchService, SearchResponse } from '../../services/search.service';
import { BrowseResponse } from '../../services/search.service';
import { UserService } from '../../services/user.service';
import { PlaylistService } from '../../services/playlist.service';

import { SongTableManager } from '../../shared/songTableManager.module';

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
    private users: User[];

    @ViewChild(MatSort) sort: any;
    private songTableManager: SongTableManager;

    private errorMessage: string;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private searchService: SearchService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.activatedRoute.params
            .subscribe((searchParams: any) => {
                this.searchQuery = searchParams['searchQuery'];
                this.search();
            });
    }

    private search(): void {
        this.searchService.search(this.searchQuery)
            .subscribe(
            (searchData: SearchResponse) => {
                this.albums = searchData.albums;
                if (this.albums) {
                    this.albums.forEach((album: Album) => {
                        album.albumArtUrl =
                            this.fileService.getAlbumImageURLByIdAndSize(album.id, true);
                    });
                }

                this.artists = searchData.artists;
                if (this.artists) {
                    this.artists.forEach((artist: Artist) => {
                        artist.artistImageUrl =
                            this.fileService.getArtistImageURLByIdAndSize(artist.id, true);
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

                this.songTableManager = new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.songTableManager.setSongs(searchData.songs);
                setTimeout(() => {
                    this.songTableManager.setSort(this.sort);
                }, 2000);

                this.users = searchData.users;
                if (this.users) {
                    this.users.forEach((user: User) => {
                        // Set user profile image to default url if none exists
                        this.userService.getHasImageById(user.id)
                            .subscribe((hasImage: boolean) => {
                                if (hasImage) {
                                    user.profileImageUrl = this.fileService.getUserImageURLById(user.id);
                                } else {
                                    user.profileImageUrl = '/assets/images/no_artist_picture.png';
                                }
                            });
                    });
                }
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            });
    }
}
