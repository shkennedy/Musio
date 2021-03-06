import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { FileService } from './file.service';

import { Album } from '../models/album.model';
import { Song } from '../models/song.model';

@Injectable()
export class AlbumService {

    private static ALBUM_URL = '/album';
    private static GET_URL = '/get';
    private static ADD_URL = '/add';
    private static DELETE_URL = '/delete';

    constructor(
        private router: Router,
        private fileService: FileService,
        private httpRequest: HttpRequestService
    ) { }

    public getAlbumById(albumId: number): Observable<Album> {
        return this.httpRequest.get(AlbumService.ALBUM_URL + AlbumService.GET_URL, albumId)
            .map((response: ApiResponse) => {
                const album: Album = response.responseData;
                for (let trackNumber = 0; trackNumber < album.songs.length; trackNumber += 1) {
                    album.songs[trackNumber].album = album;
                    album.songs[trackNumber].artist = album.artists[0];
                    album.songs[trackNumber].trackNumber = trackNumber + 1;
                    album.songs[trackNumber].duration = Math.floor(album.songs[trackNumber].duration / 1000);
                    album.songs[trackNumber].durationString =
                        `${Math.floor(album.songs[trackNumber].duration / 60)}:`;
                    const seconds = album.songs[trackNumber].duration % 60;
                    album.songs[trackNumber].durationString += (seconds < 10) ? `0${seconds}` : seconds;
                }
                if (album.albumArt) {
                    album.albumArtUrl =
                        this.fileService.getAlbumImageURLByIdAndSize(album.id, true);
                }
                return album;
            });
    }

    public addAlbum(album: Album): Observable<boolean> {
        return this.httpRequest.post(AlbumService.ALBUM_URL + AlbumService.ADD_URL, album)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public updateAlbum(album: Album): Observable<boolean> {
        return this.httpRequest.put(AlbumService.ALBUM_URL + AlbumService.ADD_URL, album)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public deleteAlbum(albumId: number): Observable<boolean> {
        return this.httpRequest.delete(AlbumService.ALBUM_URL + AlbumService.DELETE_URL, albumId)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public sortAlbumsByReleaseDate(albums: Album[], newestFirst: boolean): void {
        albums.sort((a: Album, b: Album) => {
            if (a.releaseDate < b.releaseDate && newestFirst) {
                return 1;
            }
            return -1;
        });
    }

    public sortAlbumsByTitle(albums: Album[], ascending: boolean): void {
        albums.sort((a: Album, b: Album) => {
            if (a.title > b.title && ascending) {
                return 1;
            }
            return -1;
        });
    }

    public sortAlbumBySongTitle(album: Album, ascending: boolean): void {
        if (ascending) {
            album.songs.sort((a: Song, b: Song) => {
                if (a.title > b.title) {
                    return 1;
                }
                return -1;
            });
        } else {
            album.songs.sort((a: Song, b: Song) => {
                if (a.title < b.title) {
                    return 1;
                }
                return -1;
            });
        }
    }

    public sortAlbumByTrack(album: Album, ascending: boolean): void {
        if (ascending) {
            album.songs.sort((a: Song, b: Song) => {
                if (a.trackNumber > b.trackNumber) {
                    return 1;
                }
                return -1;
            });
        } else {
            album.songs.sort((a: Song, b: Song) => {
                if (a.trackNumber < b.trackNumber) {
                    return 1;
                }
                return -1;
            });
        }
    }
}
