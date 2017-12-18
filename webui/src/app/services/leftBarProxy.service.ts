import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class LeftBarProxyService {

    private static REFRESH_PLAYLISTS_TAG = 'REFRESH_PLAYLISTS';
    private static RELINQUISH_FOCUS_TAG = 'RELINQUISH_FOCUS';

    private listeners = {};

    constructor() { }

    public registerListeners(relinquishFocusListener, refreshPlaylistsListener): void {
        this.listeners[LeftBarProxyService.RELINQUISH_FOCUS_TAG] = relinquishFocusListener;
        this.listeners[LeftBarProxyService.REFRESH_PLAYLISTS_TAG] = refreshPlaylistsListener;
    }

    public relinquishFocus(): void {
        this.listeners[LeftBarProxyService.RELINQUISH_FOCUS_TAG]();
    }

    public refreshPlaylists(): void {
        this.listeners[LeftBarProxyService.REFRESH_PLAYLISTS_TAG]();
    }
}
