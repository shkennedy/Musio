import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArtistService } from '../../../services/artist.service';
import { FavoritesService } from '../../../services/favorites.service';

import { Artist } from '../../../models/artist.model';
import { Album } from '../../../models/album.model';
import { Genre } from '../../../models/genre.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'artist-detail',
    templateUrl: './artist-detail.component.html',
    styleUrls: ['./artist-detail.component.css'],
    providers: [ArtistService, FavoritesService]
})
export class ArtistDetailComponent implements OnInit {

    @Input() artistId: number;

    private artist: Artist;
    private followerCount: number;
    private isFollowed: boolean = false;
    private relatedArtists: Artist[];
    private errorMessage: string;

    constructor(
        private router: Router,
        private artistService: ArtistService,
        private favoritesService: FavoritesService
    ) { }

    ngOnInit() {
        this.artistService.getArtistById(this.artistId)
        .subscribe(
            (artist: Artist) => {
                this.artist = artist;
            },
            (error: any) => {
                this.errorMessage = error;
            }
        );

        this.artistService.getArtistFollowerCount(this.artistId)
        .subscribe(
            (followerCount: number) => {
                this.followerCount = followerCount;
            },
            (error: any) => {
                console.log(error);
            }
        )

        this.favoritesService.getFavoriteArtists()
        .subscribe(
            (artists: Artist[]) => {
                if (artists) {
                    for (let i = 0; i < artists.length; ++i) {
                        if (artists[i].id == this.artist.id) {
                            this.isFollowed = true;
                            console.log('artist-detail.component found artist is followed');
                        }
                    }
                }
            },
            (error: any) => {
                console.log(error);
            }
        );

        this.artistService.getRelatedArtists(this.artistId)
        .subscribe(
            (relatedArtists: Artist[]) => {
                this.relatedArtists = relatedArtists;
            },
            (error: any) => {
                console.log(error);
            }
        );
    }

    followArtist() {
        this.favoritesService.addFavoriteArtistById(this.artist.id)
            .subscribe(
                (success: boolean) => {
                    this.isFollowed = true;
                },
                (error: any) => {
                    console.log(error);
                }
            );
    }

    unfollowArtist() {
        this.favoritesService.removeFavoriteArtistById(this.artist.id)
            .subscribe(
                (success: boolean) => {
                    this.isFollowed = false;
                },
                (error: any) => {
                    console.log(error);
                }
            );
    }
}
