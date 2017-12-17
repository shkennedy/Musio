import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSort, MatTableDataSource } from '@angular/material';

import { AlbumService } from '../../../services/album.service';
import { ArtistService } from '../../../services/artist.service';
import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';
import { PlaylistService } from '../../../services/playlist.service';

import { SongTableManager } from '../../../shared/songTableManager.module';

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

    private isLoaded = false;
    private artist: Artist;
    private followerCount: number;
    private isFollowed = false;
    private relatedArtists: Artist[];

    private albums: Map<number, Album>;
    @ViewChild(MatSort) sort: any;
    private albumSongTableManagers: Map<number, SongTableManager> = new Map();

    private errorMessage: string;

    constructor(
        private router: Router,
        private albumService: AlbumService,
        private artistService: ArtistService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService
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
                        this.albums = new Map();

                        this.artist.albums.forEach((album: Album) => {
                            this.albums.set(album.id, album);

                            const albumSongTableManager =
                                new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                            albumSongTableManager.setSongs(album.songs);
                            this.albumSongTableManagers.set(album.id, albumSongTableManager);

                            setTimeout(() => {
                                albumSongTableManager.setSort(this.sort);
                            }, 3000);
                        });

                        // Check if favorited
                        this.favoritesService.getFavoriteAlbums()
                            .subscribe(
                            (favoriteAlbums: Album[]) => {
                                if (albums) {
                                    albums.forEach((album: Album) => {
                                        album.isFavorited = false;
                                        favoriteAlbums.forEach((favoriteAlbum: Album) => {
                                            if (album.id === favoriteAlbum.id) {
                                                album.isFavorited = true;
                                            }
                                        });
                                    });
                                }
                            },
                            (error: any) => {
                                this.errorMessage = error;
                            });

                        this.favoritesService.getFavoriteSongs()
                            .subscribe(
                            (favoriteSongs: Song[]) => {
                                albums.forEach((album: Album) => {
                                    album.songs.forEach((song: Song) => {
                                        song.isFavorited = false;
                                        favoriteSongs.forEach((favoritedSong: Song) => {
                                            if (favoritedSong.id === song.id) {
                                                song.isFavorited = true;
                                            }
                                        });
                                    });
                                });
                            },
                            (error: any) => {
                                console.log('error fetching favorite songs');
                            });

                        this.isLoaded = true;
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

    private favoriteAlbum(albumId: number): void {
        this.favoritesService.addFavoriteAlbumById(albumId)
            .subscribe(
            (success: boolean) => {
                this.albums.get(albumId).isFavorited = true;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private unfavoriteAlbum(albumId: number): void {
        this.favoritesService.removeFavoriteAlbumById(albumId)
            .subscribe(
            (success: boolean) => {
                this.albums.get(albumId).isFavorited = false;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private gotoAlbum(albumId: number): void {
        this.router.navigate(['/albums', albumId]);
    }

    private gotoRelatedArtist(relatedArtistId: number): void {
        this.router.navigate(['/albums']) // garbage link to force page reload
            .then(() => {
                this.router.navigate(['/artists', relatedArtistId]);
            });
    }

    private playArtist(): void {
        this.audioPlayerProxyService.playArtist(this.artist.id);
    }

    private addArtistToQueue(): void {
        this.audioPlayerProxyService.addArtistToQueue(this.artist.id);
    }

    private playAlbum(albumId: number): void {
        this.audioPlayerProxyService.playAlbum(albumId);
    }

    private addAlbumToQueue(albumId: number): void {
        this.audioPlayerProxyService.addAlbumToQueue(albumId);
    }

    private getSongTableManager(albumId: number): SongTableManager {
        return this.albumSongTableManagers.get(albumId);
    }
}
