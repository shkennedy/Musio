import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Playlist } from '../models/playlist.model';

@Injectable()
export class PlaylistService {

    private static PLAYLIST_URL     = '/playlist';
    private static CREATE_URL       = PlaylistService.PLAYLIST_URL + '/create';
    private static ADD_SONG_URL     = PlaylistService.PLAYLIST_URL + '/addsong';
    private static REMOVE_SONG_URL  = PlaylistService.PLAYLIST_URL + '/removesong';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getPlaylistById(playlistId: number): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.PLAYLIST_URL, playlistId)
        .map((response: ApiResponse) => {
            return response.responseData;
        });
    }

    public createPlaylist(playlist: Playlist): Observable<boolean> {
        return this.httpRequest.post(PlaylistService.CREATE_URL, playlist)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public updatePlaylist(playlist: Playlist): Observable<Playlist> {
        return this.httpRequest.post(PlaylistService.PLAYLIST_URL, playlist)
        .map((response: ApiResponse) => {
            return response.responseData;
        });
    }

    public deletePlaylist(playlistId: number): Observable<boolean> {
        return this.httpRequest.delete(PlaylistService.PLAYLIST_URL, playlistId)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public addSong(playlistId: number, songId: number): Observable<boolean> {
        const body = {
            'playlistId': playlistId,
            'songId': songId
        };
        return this.httpRequest.post(PlaylistService.ADD_SONG_URL, body)
        .map((response: ApiResponse) => {
            return response.responseData;
        });
    }

    public removeSong(playlistId: number, songId: number): Observable<boolean> {
        const body = {
            'playlistId': playlistId,
            'songId': songId
        };
        return this.httpRequest.post(PlaylistService.REMOVE_SONG_URL, body)
        .map((response: ApiResponse) => {
            return response.responseData;
        });
    }
}
