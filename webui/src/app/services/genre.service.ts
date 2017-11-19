import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Song } from '../models/song.model';
import { Genre } from '../models/genre.model';

@Injectable()
export class GenreService {

    private static GENRE_URL: string    = '/genre';
    private static TOP_URL: string      = '/top';
    private static TOP_ALBUMS_URL: string = GenreService.GENRE_URL + '/albums' + GenreService.TOP_URL;
    private static TOP_ARTISTS_URL: string = GenreService.GENRE_URL + '/artists' + GenreService.TOP_URL;
    private static TOP_PLAYLISTS_URL: string = GenreService.GENRE_URL + '/playlists' + GenreService.TOP_URL;
    private static TOP_SONGS_URL: string = GenreService.GENRE_URL + '/songs' + GenreService.TOP_URL;

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getTopAlbumsByGenreId(id: number): Album[] {
        return this.httpRequest.get(GenreService.TOP_URL, {genreId: id})
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }

    public getTopArtistsByGenreId(id: number): Artist[] {
        return this.httpRequest.get(GenreService.TOP_URL, {genreId: id})
        .subscribe((artists: Artist[]) => {
            return artists;
        });
    }

    public getTopSongsGenreId(id: number): Song[] {
        return this.httpRequest.get(GenreService.TOP_URL, {genreId: id})
        .subscribe((songs: Song[]) => {
            return songs;
        });
    }
}
