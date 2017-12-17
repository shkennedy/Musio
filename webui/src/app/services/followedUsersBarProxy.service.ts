import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

@Injectable()
export class FollowedUsersBarProxyService {

    private static REFRESH_FOLLOWED_USERS_TAG = 'REFRESH_FOLLOWED_USERS';

    private listeners = {};

    constructor() { }

    public registerListeners(refreshFollowedUsersListener): void {
        this.listeners[FollowedUsersBarProxyService.REFRESH_FOLLOWED_USERS_TAG] = refreshFollowedUsersListener;
    }

    public refreshFollowedUsers(): void {
        this.listeners[FollowedUsersBarProxyService.REFRESH_FOLLOWED_USERS_TAG]();
    }
}
