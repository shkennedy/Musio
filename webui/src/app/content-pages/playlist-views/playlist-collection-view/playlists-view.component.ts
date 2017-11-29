import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';
import { PlaylistService } from '../../../services/playlist.service';

import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-playlists-view',
    templateUrl: './playlists-view.component.html',
    styleUrls: ['./playlists-view.component.css']
})
export class PlaylistsViewComponent implements OnInit {

    private playlists: Playlist[];
    private isEmpty: boolean = true;

    private errorMessage: string;

    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService
    ) { }

    ngOnInit() {
        this.favoritesService.getFavoritePlaylists()
        .subscribe(
            (playlists: Playlist[]) => {
                this.playlists = playlists;
                this.isEmpty = false;
            },
            (error: any) => {
                this.errorMessage = error;
            }
        );
    }
}
