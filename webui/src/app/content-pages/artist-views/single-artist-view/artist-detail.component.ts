import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';

import { AlbumService } from '../../../services/album.service';
import { ArtistService } from '../../../services/artist.service';
import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';

import { Artist } from '../../../models/artist.model';
import { Album } from '../../../models/album.model';
import { Genre } from '../../../models/genre.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-artist-detail',
    templateUrl: './artist-detail.component.html',
    styleUrls: ['./artist-detail.component.css'],
    providers: [ArtistService, FavoritesService, FileService]
})
export class ArtistDetailComponent implements OnInit {

    private artist: Artist;
    private albumTablesData: Map<number, MatTableDataSource<Song>>;
    private followerCount: number;
    private isFollowed = false;
    private relatedArtists: Artist[];
    private titleSort = false;
    private ascendingOrder = true;

    private errorMessage: string;

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private artistService: ArtistService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        this.artistService.getArtistById(Number(url[url.length - 1]))
            .subscribe(
            (artist: Artist) => {
                this.artist = artist;
                this.artistService.getArtistAlbumsById(artist.id)
                    .subscribe(
                    (albums: Album[]) => {
                        this.artist.albums = albums;
                        this.albumTablesData = new Map();
                        this.artist.albums.forEach((album: Album) => {
                            console.log('wateeee');
                            console.log(`setting ${album.id} ${album}`);
                            this.albumTablesData.set(album.id, new MatTableDataSource(album.songs));
                        });
                    },
                    (error: any) => {
                        console.log(error.toString());
                    });
                this.artistService.getArtistFollowerCount(artist.id)
                    .subscribe(
                    (followerCount: number) => {
                        this.followerCount = followerCount;
                    },
                    (error: any) => {
                        console.log(error.toString());
                    });
                this.artistService.getRelatedArtists(artist.id)
                    .subscribe(
                    (relatedArtists: Artist[]) => {
                        this.relatedArtists = relatedArtists;
                    },
                    (error: any) => {
                        console.log(error.toString());
                    });
                this.favoritesService.getFavoriteArtists()
                    .subscribe(
                    (artists: Artist[]) => {
                        if (artists) {
                            for (let i = 0; i < artists.length; ++i) {
                                if (artists[i].id === this.artist.id) {
                                    this.isFollowed = true;
                                    console.log('artist-detail.component found artist is followed');
                                }
                            }
                        }
                    },
                    (error: any) => {
                        console.log(error.toString());
                    });
            },
            (error: any) => {
                this.errorMessage = error;
            });
    }

    private followArtist(): void {
        this.favoritesService.addFavoriteArtistById(this.artist.id)
            .subscribe(
            (success: boolean) => {
                this.isFollowed = true;
                this.followerCount += 1;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private unfollowArtist(): void {
        this.favoritesService.removeFavoriteArtistById(this.artist.id)
            .subscribe(
            (success: boolean) => {
                this.isFollowed = false;
                this.followerCount -= 1;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private playArtist(): void {
        this.audioPlayerProxyService.playArtist(this.artist.id);
    }

    private addArtistToQueue(): void {
        this.audioPlayerProxyService.addArtistToQueue(this.artist.id);
    }

    private gotoRelatedArtist(relatedArtistId: number): void {
        this.router.navigate(['/albums']) // garbage link to force page reload
            .then(() => {
                this.router.navigate(['/artists', relatedArtistId]);
            });
    }

    private sort(): void {
        this.albumService.sortAlbumsByReleaseDate(this.artist.albums, this.ascendingOrder);
    }

    private getAlbumData(albumId: number) {
        console.log(albumId);
        console.log(this.albumTablesData.get(albumId));
        return this.albumTablesData.get(albumId);
    }
}
