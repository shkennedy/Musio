import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Genre } from '../models/genre.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';
import { Station } from '../models/station.model';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest, HttpParams } from '@angular/common/http';

@Injectable()
export class FavoritesService {

	private static FAVORITES_URL: string = '/user/favorites';
	private static ADD_URL: string		 = FavoritesService.FAVORITES_URL + '/add';
	private static REMOVE_URL: string	 = FavoritesService.FAVORITES_URL + '/remove';
	private static ALBUMS_URL: string 	 = '/albums';
	private static ALBUM_URL: string   = '/album';
	private static ARTISTS_URL: string 	 = '/artists';
	private static ARTIST_URL: string   = '/artist';
	private static GENRE_URL: string	 = '/genres';
	private static PLAYLISTS_URL: string  = '/playlists';
	private static PLAYLIST_URL: string  = '/playlist';
	private static SONGS_URL: string 	 = '/songs';
	private static SONG_URL: string 	 = '/song';
	private static STATION_URL: string	 = '/stations';

    constructor(
        private router: Router,
        private http: HttpClient,
        private httpRequest: HttpRequestService
	) { }

	private getFavoritesByUrl(url: string): Observable<any> {
		return this.httpRequest.get(FavoritesService.FAVORITES_URL + url)
		.map((response: ApiResponse) => {
			if (response.success) {
				return response.data;
			}
			return null;
		});
	}

	private addFavoriteByUrlAndId(url: string, id: number): Observable<boolean> {
		return this.httpRequest.get(FavoritesService.ADD_URL + url, id)
		.map((response: ApiResponse) => {
			return response.success;
		});
	}

	private removeFavoriteByUrlAndId(url: string, id: number): Observable<boolean> {
		return this.httpRequest.get(FavoritesService.REMOVE_URL + url, id)
		.map((response: ApiResponse) => {
			return response.success;
		});
	}

    public getFavoriteAlbums(): Observable<Album[]> {
		// return this.getFavoritesByUrl(FavoritesService.ALBUMS_URL);
		return this.http.get(FavoritesService.FAVORITES_URL + FavoritesService.ALBUMS_URL);
	}

	public addFavoriteAlbumById(albumId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.ALBUM_URL, albumId);
	}

	public removeFavoriteAlbumById(albumId: number): Observable<boolean> {
		return this.removeFavoriteByUrlAndId(FavoritesService.ALBUM_URL, albumId);
	}

	public getFavoriteArtists(): Observable<Artist[]> {
	    return this.getFavoritesByUrl(FavoritesService.ARTISTS_URL);
	}

	public addFavoriteArtistById(artistId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.ALBUM_URL, artistId);
	}

	public removeFavoriteArtistById(artistId: number): Observable<boolean> {
		return this.removeFavoriteByUrlAndId(FavoritesService.ALBUM_URL, artistId);
	}

	public getFavoriteGenres(): Observable<Genre[]> {
		return this.getFavoritesByUrl(FavoritesService.GENRE_URL);
	}

	public addFavoriteGenreById(genreId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.GENRE_URL, genreId);
	}

	public removeFavoriteGenreById(genreId: number): Observable<boolean> {
		return this.removeFavoriteByUrlAndId(FavoritesService.GENRE_URL, genreId);
	}

	public getFavoritePlaylists(): Observable<Playlist[]> {
		return this.getFavoritesByUrl(FavoritesService.PLAYLISTS_URL);
	}

	public addFavoritePlaylistById(playlistId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.PLAYLIST_URL, playlistId);
	}

	public removeFavoritePlaylistById(playlistId: number): Observable<boolean> {
		return this.removeFavoriteByUrlAndId(FavoritesService.PLAYLIST_URL, playlistId);
	}

	public getFavoriteSongs(): Observable<Song[]> {
		return this.getFavoritesByUrl(FavoritesService.SONGS_URL);
	}

	public addFavoriteSongById(songId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.SONG_URL, songId);
	}

	public removeFavoriteSongById(songId: number): Observable<boolean> {
		return this.removeFavoriteByUrlAndId(FavoritesService.SONG_URL, songId);
	}

	public getFavoriteStations(): Observable<Station[]> {
		return this.getFavoritesByUrl(FavoritesService.STATION_URL);
	}

	public addFavoriteStationById(stationId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.STATION_URL, stationId);
	}

	public removeFavoriteStationById(stationId: number): Observable<boolean> {
		return this.addFavoriteByUrlAndId(FavoritesService.STATION_URL, stationId);
	}
}
