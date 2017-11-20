import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

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

    public getTopAlbumsByGenreId(id: number): Observable<Album[]> {
        return this.httpRequest.get(GenreService.TOP_URL, id)
        .map((response: ApiResponse) => {
            return response.data;
        });
    }

    public getTopArtistsByGenreId(id: number): Observable<Artist[]> {
        return this.httpRequest.get(GenreService.TOP_URL, id)
        .map((response: ApiResponse) => {
            if (response.success) {
				return response.data;
			}
			return null; 
        });
    }

    public getTopSongsGenreId(id: number): Observable<Song[]> {
        return this.httpRequest.get(GenreService.TOP_URL, id)
        .map((response: ApiResponse) => {
            if (response.success) {
				return response.data;
			}
			return null; 
        });
    }
}
