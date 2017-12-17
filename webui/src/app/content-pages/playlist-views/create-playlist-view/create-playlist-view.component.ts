import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';
import { PlaylistService } from '../../../services/playlist.service';

import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-create-playlist',
    templateUrl: './create-playlist.component.html',
    styleUrls: ['./create-playlist.component.css']
})
export class PlaylistsViewComponent implements OnInit {

    private playlists: Playlist[];

    private errorMessage: string;

    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService
    ) { }

    ngOnInit() {
        this.favoritesService.getFavoritePlaylists()
            .subscribe((playlists: Playlist[]) => {
               this.playlists = playlists;
            },
            (error: any) => {
                this.errorMessage = error;
            });
    }
}
