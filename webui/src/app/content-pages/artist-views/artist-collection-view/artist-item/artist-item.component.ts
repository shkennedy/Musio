import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../../services/favorites.service';
import { ArtistService } from '../../../../services/artist.service';
import { FileService } from '../../../../services/file.service';

import { Artist } from '../../../../models/artist.model';

@Component({
    selector: 'app-artist-item',
    templateUrl: './artist-item.component.html',
    styleUrls: ['./artist-item.component.css'],
    providers: [ArtistService, FavoritesService, FileService]
})
export class ArtistItemComponent implements OnInit { // AM I EVEN USED????

    @Input() artistId: number;

    private artist: Artist;
    private isFavorited: boolean;

    private errMsg: string;

    constructor(
        private router: Router,
        private artistService: ArtistService,
        private favoritesService: FavoritesService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        this.artistService.getArtistById(this.artistId)
            .subscribe(
                (artist: Artist) => {
                    this.artist = artist;
                    this.artist.artistImageUrl =
                        this.fileService.getArtistImageURLByIdAndSize(artist.id, true);
                },
                (error: any) => {
                    this.errMsg = error;
                }
        );
    }
}
