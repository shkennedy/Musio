import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { FileService } from './file.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';

@Injectable()
export class ArtistService {

    private static ARTIST_URL = '/artist';
    private static GET_URL = '/get';
    private static ALBUMS_URL = '/albums';
    private static FOLLOWER_COUNT_URL = '/favoritesCount';
    private static RELATED_URL = '/related';
    private static ADD_URL = '/add';
    private static DELETE_URL = '/delete';

    constructor(
        private router: Router,
        private fileService: FileService,
        private httpRequest: HttpRequestService
    ) { }

    public getArtistById(artistId: number): Observable<Artist> {
        return this.httpRequest.get(ArtistService.ARTIST_URL + ArtistService.GET_URL, artistId)
            .map((response: ApiResponse) => {
                if (response.success) {
                    const artist: Artist = response.responseData;
                    if (artist.artistImage) {
                        artist.artistImageUrl =
                            this.fileService.getArtistImageURLByIdAndSize(artist.artistImage.id, true);
                    }
                    return response.responseData;
                }
                return null;
            });
    }

    getArtistAlbumsById(artistId: number): Observable<Album[]> {
        const url = ArtistService.ARTIST_URL + ArtistService.GET_URL + '/' +
                    artistId + ArtistService.ALBUMS_URL;
        return this.httpRequest.get(url)
            .map((response: ApiResponse) => {
                return response.responseData;
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
            });
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
