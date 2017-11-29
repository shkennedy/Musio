import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { ArtistService } from '../../../../services/artist.service';
import { Artist } from '../../../../models/artist.model';

@Component({
    selector: 'artist-item',
    templateUrl: './artist-item.component.html',
    styleUrls: ['./artist-item.component.css'],
    providers: [ArtistService, FavoritesService]
})
export class ArtistItemComponent implements OnInit {

    @Input() artistId: number;

    private artist: Artist;
    private isFavorited: boolean;

    private errMsg: string;

    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private artistService: ArtistService
    ) { }

    ngOnInit() {
        this.artistService.getArtistById(this.artistId)
            .subscribe(
                (artist: Artist) => {
                    this.artist = artist;
                },
                (error: any) => {
                    this.errMsg = error;
                }
        );
    }
}
