import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';

import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Genre } from '../models/genre.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';
import { Station } from '../models/station.model';

@Injectable()
export class FavoritesService {

	private static FAVORITES_URL: string = '/favorites';
	private static ADD_URL: string		 = FavoritesService.FAVORITES_URL + '/add';
	private static REMOVE_URL: string	 = FavoritesService.FAVORITES_URL + '/remove';
	private static ALBUM_URL: string 	 = '/albums';
	private static ARTIST_URL: string 	 = '/artists';
	private static GENRE_URL: string	 = '/genres';
	private static PLAYLIST_URL: string  = '/playlists';
	private static SONG_URL: string 	 = '/songs';
	private static STATION_URL: string	 = '/stations';

    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private httpRequest: HttpRequestService
	) { }
	
	private getFavoritesByUrl(url: string): any {
		return this.httpRequest.get(FavoritesService.FAVORITES_URL + url)
		.subscribe((response: ApiResponse) => {
			if (response.success) {
				return response.data;
			}
			return null;
		});
	}

	private addFavoriteByUrlAndId(url: string, id: number): boolean {
		return this.httpRequest.get(FavoritesService.ADD_URL + url)
		.subscribe((response: ApiResponse) => {
			return response.success;
		});
	}

	private removeFavoriteByUrlAndId(url: string, id: number): boolean {
		return this.httpRequest.get(FavoritesService.REMOVE_URL + url)
		.subscribe((response: ApiResponse) => {
			return response.success;
		});
	}

    public getFavoriteAlbums(): Album[] {
		return this.getFavoritesByUrl(FavoritesService.ALBUM_URL);
	}
	
	public addFavoriteAlbumById(albumId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.ALBUM_URL, albumId);
	}

	public removeFavoriteAlbumById(albumId: number): boolean {
		return this.removeFavoriteByUrlAndId(FavoritesService.ALBUM_URL, albumId);
	}

	public getFavoriteArtists(): Artist[] {
	    return this.getFavoritesByUrl(FavoritesService.ARTIST_URL);
	}

	public addFavoriteArtistById(artistId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.ALBUM_URL, artistId);
	}

	public removeFavoriteArtistById(artistId: number): boolean {
		return this.removeFavoriteByUrlAndId(FavoritesService.ALBUM_URL, artistId);
	}

	public getFavoriteGenres(): Genre[] {
		return this.getFavoritesByUrl(FavoritesService.GENRE_URL);
	}

	public addFavoriteGenreById(genreId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.GENRE_URL, genreId);
	}

	public removeFavoriteGenreById(genreId: number): boolean {
		return this.removeFavoriteByUrlAndId(FavoritesService.GENRE_URL, genreId);
	}

	public getFavoritePlaylists(): Playlist[] {
		return this.getFavoritesByUrl(FavoritesService.PLAYLIST_URL);
	}

	public addFavoritePlaylistById(playlistId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.PLAYLIST_URL, playlistId);
	}

	public removeFavoritePlaylistById(playlistId: number): boolean {
		return this.removeFavoriteByUrlAndId(FavoritesService.PLAYLIST_URL, playlistId);
	}

	public getFavoriteSongs(): Song[] {
		return this.getFavoritesByUrl(FavoritesService.SONG_URL);
	}

	public addFavoriteSongById(songId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.SONG_URL, songId);
	}

	public removeFavoriteSongById(songId: number): boolean {
		return this.removeFavoriteByUrlAndId(FavoritesService.SONG_URL, songId);
	}

	public getFavoriteStations(): Station[] {
		return this.getFavoritesByUrl(FavoritesService.STATION_URL);
	}

	public addFavoriteStationById(stationId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.STATION_URL, stationId);
	}

	public removeFavoriteStationById(stationId: number): boolean {
		return this.addFavoriteByUrlAndId(FavoritesService.STATION_URL, stationId);
	}
}