import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class PlaylistService {

    private static PLAYLIST_URL = '/playlist';
    private static CREATE_URL = PlaylistService.PLAYLIST_URL + '/create';
    private static ADD_SONG_URL = PlaylistService.PLAYLIST_URL + '/addsong';
    private static REMOVE_SONG_URL = PlaylistService.PLAYLIST_URL + '/removesong';
    private static SET_COLLABORATION_URL = PlaylistService.PLAYLIST_URL + '/setCollaborative';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getPlaylistById(playlistId: number): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.PLAYLIST_URL, playlistId)
            .map((response: ApiResponse) => {
                const playlist: Playlist = response.responseData;
                for (let trackNumber = 1; trackNumber <= playlist.songs.length; trackNumber += 1) {
                    playlist.songs[trackNumber].trackNumber = trackNumber;
                    playlist.songs[trackNumber].duration = Math.floor(playlist.songs[trackNumber].duration / 1000);
                    playlist.songs[trackNumber].durationString =
                        `${Math.floor(playlist.songs[trackNumber].duration / 60)}:`;
                    const seconds = playlist.songs[trackNumber].duration % 60;
                    playlist.songs[trackNumber].durationString += (seconds < 10) ? `0${seconds}` : seconds;
                }
                return playlist;
            });
    }

    public createPlaylist(playlistName: string): Observable<Playlist> {
        return this.httpRequest.getWithUrlParams(PlaylistService.CREATE_URL, playlistName)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public setPlaylistCollaborationById(playlistId: number, isCollaboartive): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.SET_COLLABORATION_URL + '/' + playlistId + '/' + isCollaboartive)
            .map((response: ApiResponse) => {
                return response.responseData;
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

    public sortByTitle(playlist: Playlist, ascending: boolean): void {
        if (ascending) {
            playlist.songs.sort((a: Song, b: Song) => {
                if (a.title > b.title) {
                    return 1;
                }
                return -1;
            });
        } else {
            playlist.songs.sort((a: Song, b: Song) => {
                if (a.title < b.title) {
                    return 1;
                }
                return -1;
            });
        }
    }

    public sortByTrack(playlist: Playlist, ascending: boolean): void {
        if (ascending) {
            playlist.songs.sort((a: Song, b: Song) => {
                if (a.trackNumber > b.trackNumber) {
                    return 1;
                }
                return -1;
            });
        } else {
            playlist.songs.sort((a: Song, b: Song) => {
                if (a.trackNumber < b.trackNumber) {
                    return 1;
                }
                return -1;
            });
        }
    }
}
