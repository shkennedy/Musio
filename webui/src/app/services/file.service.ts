import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';

@Injectable()
export class FileService {

    private static RESOURCE_URL = '/resource';
    private static GENERIC_GET_URL: string = FileService.RESOURCE_URL + '/get';
    private static SONG_HIBR_URL: string = FileService.RESOURCE_URL + '/highBitrateSongFile';
    private static SONG_LOBR_URL: string = FileService.RESOURCE_URL + '/lowBitrateSongFile';
    private static ALBUM_FULL_URL: string = FileService.RESOURCE_URL + '/albumArtworkFullFile';
    private static ALBUM_THUMB_URL: string = FileService.RESOURCE_URL + '/albumArtworkThumbnailFile';
    private static ARTIST_FULL_URL: string = FileService.RESOURCE_URL + '/artistImageFullFile';
    private static ARTIST_THUMB_URL: string = FileService.RESOURCE_URL + '/artistImageThumbnailFile';
    private static USER_FULL_URL: string = FileService.RESOURCE_URL + '/userProfileImage';
    private static BASE_URL = 'http://localhost:8080';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getFileURLById(fileId: number): string {
        return FileService.GENERIC_GET_URL + `/${fileId}`;
    }

    public getAlbumImageURLByIdAndSize(albumId: number, useFullSize: boolean): string {
        return FileService.BASE_URL +
        ((useFullSize) ? FileService.ALBUM_FULL_URL : FileService.ALBUM_THUMB_URL) + `/${albumId}`;
    }

    public getArtistImageURLByIdAndSize(artistId: number, useFullSize: boolean): string {
        return FileService.BASE_URL +
        ((useFullSize) ? FileService.ARTIST_FULL_URL : FileService.ARTIST_THUMB_URL) + `/${artistId}`;
    }

    public getSongFileURLByIdAndBitrate(songId: number, useHighBitrate: boolean): string {
        return FileService.BASE_URL +
        ((useHighBitrate) ? FileService.SONG_HIBR_URL : FileService.SONG_LOBR_URL) + `/${songId}`;
    }

    public getUserImageURLByIdAndSize(userId: number): string {
        return FileService.BASE_URL + FileService.USER_FULL_URL + `/${userId}`;
    }
}
