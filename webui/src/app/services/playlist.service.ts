import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class PlaylistService {

    private static PLAYLIST_URL = '/playlist';
    private static GET_URL = PlaylistService.PLAYLIST_URL  + '/get';
    private static CREATE_URL = PlaylistService.PLAYLIST_URL + '/create';
    private static DELETE_URL = PlaylistService.PLAYLIST_URL + '/delete';
    private static ADD_SONG_URL = PlaylistService.PLAYLIST_URL + '/addSong';
    private static REMOVE_SONG_URL = PlaylistService.PLAYLIST_URL + '/removeSong';
    private static SET_COLLABORATION_URL = PlaylistService.PLAYLIST_URL + '/setCollaborative';
    private static SET_PRIVACY_URL = PlaylistService.PLAYLIST_URL + '/setPrivate';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getPlaylistById(playlistId: number): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.GET_URL, playlistId)
            .map((response: ApiResponse) => {
                const playlist: Playlist = response.responseData;
                console.log(playlist.songs);
                for (let trackNumber = 1; trackNumber <= playlist.songs.length; trackNumber += 1) {
                    console.log(trackNumber);
                    playlist.songs[trackNumber - 1].trackNumber = trackNumber;
                    playlist.songs[trackNumber - 1].duration = Math.floor(playlist.songs[trackNumber - 1].duration / 1000);
                    playlist.songs[trackNumber - 1].durationString =
                        `${Math.floor(playlist.songs[trackNumber - 1].duration / 60)}:`;
                    const seconds = playlist.songs[trackNumber - 1].duration % 60;
                    playlist.songs[trackNumber - 1].durationString += (seconds < 10) ? `0${seconds}` : seconds;
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

    public setPlaylistCollaborationById(playlistId: number, isCollaboartive: boolean): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.SET_COLLABORATION_URL + '/' + playlistId + '/' + isCollaboartive)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public setPlaylistPrivacyById(playlistId: number, isPrivate: boolean): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.SET_PRIVACY_URL + '/' + playlistId + '/' + isPrivate)
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
        return this.httpRequest.delete(PlaylistService.DELETE_URL, playlistId)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    // public addSong(playlistId: number, songId: number): Observable<boolean> {
    //     const body = {
    //         'playlistId': playlistId,
    //         'songId': songId
    //     };
    //     return this.httpRequest.post(PlaylistService.ADD_SONG_URL, body)
    //         .map((response: ApiResponse) => {
    //             return response.responseData;
    //         });
    // }

    public addSong(playlistId: number, songId: number): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.ADD_SONG_URL + '/' + playlistId + '/' + songId)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public removeSong(playlistId: number, songId: number): Observable<Playlist> {
        return this.httpRequest.get(PlaylistService.REMOVE_SONG_URL + '/' + playlistId + '/' + songId)
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
