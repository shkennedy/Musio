import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArtistService } from '../../../services/artist.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';

import { Artist } from '../../../models/artist.model';
import { Album } from '../../../models/album.model';
import { Genre } from '../../../models/genre.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-artists-view',
    templateUrl: './artists-view.component.html',
    styleUrls: ['./artists-view.component.css'],
    providers: [ArtistService, FavoritesService, FileService]
})
export class ArtistsViewComponent implements OnInit {

    private artists: Artist[];

    constructor(
        private router: Router,
        private artistService: ArtistService,
        private favoritesService: FavoritesService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        this.favoritesService.getFavoriteArtists()
        .subscribe(
            (artists: Artist[]) => {
                if (artists.length !== 0) {
                    this.artists = artists;
                    artists.forEach((artist: Artist) => {
                        artist.artistImageUrl =
                            this.fileService.getArtistImageURLByIdAndSize(artist.id, false);
                    });
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

}
