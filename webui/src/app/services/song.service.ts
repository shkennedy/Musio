import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Song } from '../models/song.model';

@Injectable()
export class SongService {

    private static SONG_URL = '/song';
    private static GET_URL = '/get';
    private static ALBUM_INFO_URL = '/getBasicAlbumInfo';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getSongById(songId: number): Observable<Song> {
        return this.httpRequest.get(SongService.SONG_URL + SongService.GET_URL, songId)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public getSongAlbumInfo(songId: number): Observable<Album> {
        return this.httpRequest.get(SongService.SONG_URL + SongService.ALBUM_INFO_URL, songId)
            .map((response: ApiResponse) => {
                return response.responseData[0];
            });
    }

    public addSong(song: Song): Observable<boolean> {
        return this.httpRequest.post(SongService.SONG_URL, song)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public deleteSong(songId: number): Observable<boolean> {
        return this.httpRequest.delete(SongService.SONG_URL, songId)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public setDurationString(song: Song): void {
        const duration = Math.floor(song.duration / 1000);
        song.durationString =
            `${Math.floor(song.duration / 60)}:`;
        const seconds = song.duration % 60;
        song.durationString += (seconds < 10) ? `0${seconds}` : seconds;
    }
}
