import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../services/favorites.service';
import { FileService } from '../../services/file.service';
import { SearchService, SearchResponse } from '../../services/search.service';
import { BrowseResponse } from '../../services/search.service';
import { UserService } from '../../services/user.service';

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
    private songs: Map<number, Song>;
    private users: User[];

    @ViewChild(MatSort) sort: any;
    private songTableData: MatTableDataSource<Song>;
    private playButtonsVisibility: Map<number, boolean> = new Map();

    private errorMessage: string;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
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

                this.songTableData = new MatTableDataSource(searchData.songs);
                this.songs = new Map();
                searchData.songs.forEach((song: Song) => {
                    this.songs.set(song.id, song);
                    this.playButtonsVisibility.set(song.id, false);
                });

                setTimeout(() => {
                    this.songTableData.sort = this.sort;
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

    private playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    private addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
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

    private showPlay(songId: number): void {
        this.playButtonsVisibility.set(songId, true);
    }

    private hidePlay(songId: number): void {
        this.playButtonsVisibility.set(songId, false);
    }

    private getPlayVisibility(songId: number): Object {
        return { 'visibility': this.playButtonsVisibility.get(songId) ? 'visible' : 'hidden' };
    }
}
