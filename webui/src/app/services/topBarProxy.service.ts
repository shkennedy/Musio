import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class TopBarProxyService {

    private static SET_PREMIUM_TAG = 'SET_PREMIUM';
    private static REFRESH_USER_IMAGE_TAG = 'REFRESH_USER_IMAGE';

    private listeners = {};

    constructor() { }

    public registerListeners(setPremiumListener, refreshUserImageListener): void {
        this.listeners[TopBarProxyService.SET_PREMIUM_TAG] = setPremiumListener;
        this.listeners[TopBarProxyService.REFRESH_USER_IMAGE_TAG] = refreshUserImageListener;
    }

    public setPremium(isPremium: boolean): void {
        this.listeners[TopBarProxyService.SET_PREMIUM_TAG](isPremium);
    }

    public refreshUserImage(): void {
        this.listeners[TopBarProxyService.REFRESH_USER_IMAGE_TAG]();
    }
}
