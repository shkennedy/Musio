import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Song } from '../models/song.model';

@Injectable()
export class SongService {

    private static SONG_URL: string = '/song';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getSongById(songId: number): Observable<Song> {
        return this.httpRequest.get(SongService.SONG_URL, songId)
        .map((response: ApiResponse) => {
            return response.data;
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
}
