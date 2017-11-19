import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService } from './httpRequest.service';

import { Album } from '../models/album.model';
import { ApiResponse } from './httpRequest.service';

@Injectable()
export class AlbumService {

    private static ALBUM_URL: string = '/album';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getAlbumById(albumId: number): Album {
        return this.httpRequest.get(AlbumService.ALBUM_URL, {"albumId": albumId})
        .subscribe((response: ApiResponse) => {
            return response.data;
        });
    }

    public addAlbum(album: Album): boolean {
        return this.httpRequest.post(AlbumService.ALBUM_URL, album)
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }

    public updateAlbum(album: Album): boolean {
        return this.httpRequest.put(AlbumService.ALBUM_URL, album)
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }

    public deleteAlbum(albumId: number): boolean {
        return this.httpRequest.delete(AlbumService.ALBUM_URL + "/" + albumId)
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }
}
