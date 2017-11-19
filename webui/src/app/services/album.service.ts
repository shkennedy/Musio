import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';

@Injectable()
export class AlbumService {

    private static ALBUM_URL: string = '/album';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getAlbumById(albumId: number): Observable<Album> {
        return this.httpRequest.get(AlbumService.ALBUM_URL, {"albumId": albumId})
        .map((response: ApiResponse) => {
            return response.data;
        });
    }

    public addAlbum(album: Album): Observable<boolean> {
        return this.httpRequest.post(AlbumService.ALBUM_URL, album)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public updateAlbum(album: Album): Observable<boolean> {
        return this.httpRequest.put(AlbumService.ALBUM_URL, album)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public deleteAlbum(albumId: number): Observable<boolean> {
        return this.httpRequest.delete(AlbumService.ALBUM_URL + "/" + albumId)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }
}
