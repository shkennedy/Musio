import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';

@Injectable()
export class ArtistService {

    private static ARTIST_URL: string = '/artist';
    private static GET_URL: string = '/get';
    private static FOLLOWER_COUNT_URL: string = '/favoritesCount';
    private static RELATED_URL: string = '/related';
    private static ADD_URL: string = '/add';
    private static DELETE_URL: string = '/delete';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getArtistById(artistId: number): Observable<Artist> {
        return this.httpRequest.get(ArtistService.ARTIST_URL + ArtistService.GET_URL, artistId)
        .map((response: ApiResponse) => {
            if (response.success) {
                return response.responseData;
            }
            return null;
        });
    }

    public getArtistFollowerCount(artistId: number): Observable<number> {
        return this.httpRequest.get(ArtistService.ARTIST_URL + ArtistService.FOLLOWER_COUNT_URL, artistId)
        .map((response: ApiResponse) => {
            if (response.success) {
                return response.responseData;
            }
            return -1;
        });
    }

    public getRelatedArtists(artistId: number): Observable<Artist[]> {
        return this.httpRequest.get(ArtistService.ARTIST_URL + ArtistService.RELATED_URL, artistId)
        .map((response: ApiResponse) => {
            if (response.success) {
                return response.responseData;
            }
            return null;
        })
    }

    public addArtist(artist: Artist): Observable<boolean> {
        return this.httpRequest.post(ArtistService.ARTIST_URL + ArtistService.ADD_URL, artist)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public updateArtist(artist: Artist): Observable<boolean> {
        return this.httpRequest.put(ArtistService.ARTIST_URL + ArtistService.ADD_URL, artist)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public deleteArtist(artistId: number): Observable<boolean> {
        return this.httpRequest.delete(ArtistService.ARTIST_URL + ArtistService.DELETE_URL, artistId)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }
}
