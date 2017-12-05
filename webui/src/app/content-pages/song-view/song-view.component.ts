import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SongService } from '../../services/song.service';
import { FavoritesService } from '../../services/favorites.service';

import { Song } from '../../models/song.model';
import { Artist } from '../../models/artist.model';

@Component({
    selector: 'app-song-view',
    templateUrl: './song-view.component.html',
    styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {

    private songs: Song[];

    constructor(
        private router: Router,
        private songService: SongService,
        private favoritesService: FavoritesService
    ) { }


    ngOnInit() {
        this.favoritesService.getFavoriteSongs()
            .subscribe(
            (songs: Song[]) => {
                if (songs.length !== 0) {
                    this.songs = songs;
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }
}
