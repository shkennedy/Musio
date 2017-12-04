import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArtistService } from '../../../services/artist.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Artist } from '../../../models/artist.model';
import { Album } from '../../../models/album.model';
import { Genre } from '../../../models/genre.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-artists-view',
    templateUrl: './artists-view.component.html',
    styleUrls: ['./artists-view.component.css'],
    providers: [ArtistService, FavoritesService]
})
export class ArtistsViewComponent implements OnInit {

    private artists: Artist[];

    constructor(
        private router: Router,
        private artistService: ArtistService,
        private favoritesService: FavoritesService
    ) { }

    ngOnInit() {
        this.favoritesService.getFavoriteArtists()
        .subscribe(
            (artists: Artist[]) => {
                this.artists = artists;
            },
            (error: any) => {
                console.log(error.toString());
            }
        );
    }

}
