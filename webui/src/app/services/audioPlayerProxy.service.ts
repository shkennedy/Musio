import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class AudioPlayerProxyService {

    private static ADD_ALBUM_TAG = 'ADD_ALBUM';
    private static ADD_ARTIST_TAG = 'ADD_ARTIST';
    private static ADD_PLAYLIST_TAG = 'ADD_PLAYLIST';
    private static ADD_SONG_TAG = 'ADD_SONG';
    private static PLAY_ALBUM_TAG = 'PLAY_ALBUM';
    private static PLAY_ARTIST_TAG = 'PLAY_ARTIST';
    private static PLAY_PLAYLIST_TAG = 'PLAY_PLAYLIST';
    private static PLAY_SONG_TAG = 'PLAY_SONG';
    private static SET_PRIVATE_SESSION_TAG = 'SET_PRIVATE_SESSION';
    private static SET_USE_HIGH_BITRATE_TAG = 'SET_USE_HIGH_BITRATE';

    private listeners = {};

    constructor() { }

    public registerListeners(addAlbumListener, playAlbumListener,
                             addArtistListener, playArtistListener,
                             addPlaylistListener, playPlaylistListener,
                             addSongListener, playSongListener,
                             setPrivateSessionListener, setUseHighBitrateListener): void {
        this.listeners[AudioPlayerProxyService.ADD_ALBUM_TAG] = addAlbumListener;
        this.listeners[AudioPlayerProxyService.PLAY_ALBUM_TAG] = playAlbumListener;
        this.listeners[AudioPlayerProxyService.ADD_ARTIST_TAG] = addArtistListener;
        this.listeners[AudioPlayerProxyService.PLAY_ARTIST_TAG] = playArtistListener;
        this.listeners[AudioPlayerProxyService.ADD_PLAYLIST_TAG] = addPlaylistListener;
        this.listeners[AudioPlayerProxyService.PLAY_PLAYLIST_TAG] = playPlaylistListener;
        this.listeners[AudioPlayerProxyService.ADD_SONG_TAG] = addSongListener;
        this.listeners[AudioPlayerProxyService.PLAY_SONG_TAG] = playSongListener;
        this.listeners[AudioPlayerProxyService.SET_PRIVATE_SESSION_TAG] = setPrivateSessionListener;
        this.listeners[AudioPlayerProxyService.SET_USE_HIGH_BITRATE_TAG] = setUseHighBitrateListener;
    }

    public addSongToQueue(songId: number): void {
        this.listeners[AudioPlayerProxyService.ADD_SONG_TAG](songId);
    }

    public playSong(songId: number): void {
        this.listeners[AudioPlayerProxyService.PLAY_SONG_TAG](songId);
    }

    public addAlbumToQueue(albumId: number): void {
        this.listeners[AudioPlayerProxyService.ADD_ALBUM_TAG](albumId);
    }

    public playAlbum(albumId: number): void {
        this.listeners[AudioPlayerProxyService.PLAY_ALBUM_TAG](albumId);
    }

    public addArtistToQueue(artistId: number): void {
        this.listeners[AudioPlayerProxyService.ADD_ARTIST_TAG](artistId);
    }

    public playArtist(artistId: number): void {
        this.listeners[AudioPlayerProxyService.PLAY_ARTIST_TAG](artistId);
    }

    public addPlaylistToQueue(playlistId: number): void {
        this.listeners[AudioPlayerProxyService.ADD_PLAYLIST_TAG](playlistId);
    }

    public playPlaylist(playlistId: number): void {
        this.listeners[AudioPlayerProxyService.PLAY_PLAYLIST_TAG](playlistId);
    }

    public setPrivateSession(privateSession: boolean): void {
        this.listeners[AudioPlayerProxyService.SET_PRIVATE_SESSION_TAG](privateSession);
    }

    public setUseHighBitrate(useHighBitrate: boolean): void {
        this.listeners[AudioPlayerProxyService.SET_USE_HIGH_BITRATE_TAG](useHighBitrate);
    }
}
