import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Howl } from 'howler';
import { MatSort, MatTableDataSource } from '@angular/material';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { AlbumService } from '../../services/album.service';
import { ArtistService } from '../../services/artist.service';
import { SongService } from '../../services/song.service';
import { FileService } from '../../services/file.service';
import { PlaylistService } from '../../services/playlist.service';
import { UserService } from '../../services/user.service';
import { FavoritesService } from '../../services/favorites.service';
import { AdService } from '../../services/ad.service';

import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { Playlist } from '../../models/playlist.model';
import { Song } from '../../models/song.model';

@Component({
    selector: 'app-audio-player',
    templateUrl: './audio-player.component.html',
    styleUrls: ['./audio-player.component.css'],
    providers: [AlbumService, FileService, PlaylistService, SongService, UserService]
})
export class AudioPlayerComponent implements OnInit {

    private static IN_PROGRESS_TIME = 3;
    private static SONGS_PER_AD = 5;
    songCount = 0;
    adPlaying = false;

    audio: Howl;
    useHighBitrate: boolean;
    isPremiumUser: boolean;
    volume = 1.0;
    isMuted = false;
    isPlaying = false;
    isRepeating = false;
    isShuffling = false;
    isShowingLyrics = false;
    isFavorited = false;
    songProgress = 0;
    prevSongProgress = 0;
    songTimer: any;
    currentSong: Song;
    songQueue: Song[] = [];
    history: Song[] = [];
    privateMode: boolean;
    lyricsToggle = false;
    queueToggle = false;

    @ViewChild(MatSort) sort: any;
    queueTableData: MatTableDataSource<Song>;

    isHovered: boolean;

    errMsg: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private albumService: AlbumService,
        private artistService: ArtistService,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private userService: UserService,
        private favoritesService: FavoritesService,
        private adService: AdService
    ) { }

    ngOnInit() {
        // Register listeners for external callers
        this.audioPlayerProxyService.registerListeners(
            this.addAlbumToQueue, this.playAlbum,
            this.addArtistToQueue, this.playArtist,
            this.addPlaylistToQueue, this.playPlaylist,
            this.addSongToQueue, this.playSong,
            this.setPrivateMode, this.setUseHighBitrate,
            this.setIsPremiumUser
        );

        // Find user allowed bitrate
        this.userService.getIsPremium(this.setUseHighBitrate);
        this.userService.getIsPremium(this.setIsPremiumUser);

        this.userService.getPrivateSession()
            .subscribe(
            (privateMode: boolean) => {
                this.privateMode = privateMode;
            },
            (error: any) => {
                console.log(error.toString());
            });

        this.userService.getHistory()
            .subscribe(
            (history: Song[]) => {
                this.history = history;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    public addSongToQueue = (songId: number): void => {
        this.songService.getSongById(songId)
            .subscribe(
            (song: Song) => {
                if (song) {
                    this.songService.getSongAlbumInfo(song.id)
                        .subscribe((album: Album) => {
                            song.album = album;
                            this.songQueue.push(song);
                            console.log(`${this.songQueue}`);
                        });
                    this.songService.setDurationString(song);
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    public playSong = (songId: number): void => {
        this.addSongToHistory(this.currentSong);
        this._playSong(songId);
    }

    public addPlaylistToQueue(playlistId: number): void {
        this.playlistService.getPlaylistById(playlistId)
            .subscribe((playlist: Playlist) => {
                if (playlist) {
                    playlist.songs.forEach((song: Song) => {
                        this.songService.getSongAlbumInfo(song.id)
                            .subscribe((album: Album) => {
                                song.album = album;
                                this.songQueue.push(song);
                            });
                        this.songService.setDurationString(song);
                    });
                }
            });
    }

    public playPlaylist = (playlistId: number): void => {
        console.log('start');
        this.addSongToHistory(this.currentSong);
        this.playlistService.getPlaylistById(playlistId)
            .subscribe((playlist: Playlist) => {
                console.log(playlist);
                if (playlist) {
                    let firstSong = true;
                    playlist.songs.forEach((song: Song) => {
                        this.songService.getSongAlbumInfo(song.id)
                            .subscribe((album: Album) => {
                                song.album = album;
                                if (firstSong) {
                                    firstSong = false;
                                    this._playSong(song.id);
                                }
                                this.songQueue.push(song);
                            });

                        this.songService.setDurationString(song);
                    });
                }
            });
    }

    public addAlbumToQueue = (albumId: number): void => {
        this.albumService.getAlbumById(albumId)
            .subscribe((album: Album) => {
                if (album) {
                    album.songs.forEach((song: Song) => {
                        this.songService.setDurationString(song);
                        this.songQueue.push(song);
                    });
                }
            });
    }

    public playAlbum = (albumId: number): void => {
        console.log(albumId);
        this.addSongToHistory(this.currentSong);
        this.albumService.getAlbumById(albumId)
            .subscribe((album: Album) => {
                if (album) {
                    const song = album.songs.shift();
                    this._playSong(song.id);
                    album.songs.forEach((albumSong: Song) => {
                        this.songService.setDurationString(albumSong);
                        this.songQueue.push(albumSong);
                    });
                }
            });
    }

    public addArtistToQueue = (artistId: number): void => {
        this.addSongToHistory(this.currentSong);
        this.artistService.getArtistAlbumsById(artistId)
            .subscribe((albums: Album[]) => {
                albums.forEach((album: Album) => {
                    this.songQueue.concat(album.songs);
                    album.songs.forEach((song: Song) => {
                        this.songService.setDurationString(song);
                    });
                });
            });
    }

    public playArtist = (artistId: number): void => {
        this.addSongToHistory(this.currentSong);
        this.artistService.getArtistAlbumsById(artistId)
            .subscribe((albums: Album[]) => {
                let firstAlbum = true;
                const allAlbumSongs: Song[] = [];
                albums.forEach((album: Album) => {
                    if (firstAlbum) {
                        const song = album.songs.shift();
                        this._playSong(song.id);
                        firstAlbum = false;
                    }
                    allAlbumSongs.concat(album.songs);

                    album.songs.forEach((song: Song) => {
                        this.songService.setDurationString(song);
                    });
                });
                allAlbumSongs.forEach((playlistSong: Song) => {
                    this.songQueue.push(playlistSong);
                });
            });
    }

    public setPrivateMode = (privateMode: boolean): void => {
        this.privateMode = privateMode;
        console.log(privateMode);
    }

    public setUseHighBitrate = (useHighBitrate: boolean): void => {
        this.useHighBitrate = useHighBitrate;
    }

    public setIsPremiumUser = (isPremiumUser: boolean): void => {
        this.isPremiumUser = isPremiumUser;
    }

    private makeHowl(songFileURL: string): Howl {
        console.log(songFileURL);
        const newHowl: Howl = new Howl({
            src: [songFileURL],
            format: ['ogg'],
            volume: this.volume,
            autoplay: true,
            html5: true,
            onplay: this.startSongTimer,
            onpause: this.stopSongTimer,
            onend: this.playNext,
            // mute: this.isMuted,
            onloaderror: function () {
                console.log('unable to load song');
                this.errMsg = 'Unable to load song';
            },
            onplayerror: function () {
                console.log('unable to play song');
                this.errMsg = 'Unable to play song';
            }
        });
        newHowl.mute(this.isMuted);

        return newHowl;
    }

    private _playSong(songId: number): void {
        if (this.audio) {
            this.audio.pause();
        }

        // Fetch song object
        this.songService.getSongById(songId)
            .subscribe(
            (song: Song) => {
                this.currentSong = song;

                // Convert duration to seconds from ms
                // this.currentSong.duration = this.currentSong.duration / 1000;
                this.currentSong.duration = 30;
                this.songService.setDurationString(this.currentSong);

                this.currentSong.audioFileUrl = this.fileService
                    .getSongFileURLByIdAndBitrate(song.id, this.useHighBitrate);
                this.audio = this.makeHowl(this.currentSong.audioFileUrl);
                this.songProgress = 0;
                this.prevSongProgress = 0;
                this.isPlaying = true;

                this.isFavorited = false;
                this.favoritesService.getFavoriteSongs()
                    .subscribe((favoriteSongs: Song[]) => {
                        favoriteSongs.forEach((favoriteSong: Song) => {
                            if (favoriteSong.id === song.id) {
                                this.isFavorited = true;
                            }
                        });
                    });
            },
            (error: any) => {
                this.isPlaying = false;
                this.audio = null;
                this.currentSong = null;
                console.log('something went wrong ' + error.toString());
            });
    }

    private startSongTimer = (): void => {
        this.stopSongTimer();
        this.songTimer = setInterval(() => {
            if (this.songProgress === this.prevSongProgress) {
                this.songProgress += 0.1;
                this.prevSongProgress = this.songProgress;
            } else {
                this.seek();
            }
        }, 100);
    }

    private stopSongTimer = (): void => {
        if (this.songTimer) {
            clearInterval(this.songTimer);
        }
    }

    // Helper for adding current song to history,
    // Adds song to public history if not in private mode
    private addSongToHistory(song: Song): void {
        if (!song) {
            return;
        }

        this.history.push(song);
        if (!this.privateMode) {
            this.userService.addSongIdToHistory(song.id)
                .subscribe(() => {
                    // console.log(`added ${song.id} to history`);
                });
        }
    }

    private makeAdHowl(): Howl {
        const adFileURL = this.adService.getAudioAdUrl();
        const newHowl: Howl = new Howl({
            src: ['/assets/audio/ad.mp3'],
            volume: this.volume,
            autoplay: true,
            html5: true,
            onplay: this.startAd,
            onpause: this.startSongTimer,
            onend: this.finishAd,
            // mute: this.isMuted,
            onloaderror: function () {
                console.log('unable to load song');
                this.errMsg = 'Unable to load song';
            },
            onplayerror: function () {
                console.log('unable to play song');
                this.errMsg = 'Unable to play song';
            }
        });
        newHowl.mute(this.isMuted);

        return newHowl;
    }

    private startAd = (): void => {
        this.currentSong.duration = 13;
        this.adPlaying = true;
        this.stopSongTimer();
        this.songTimer = setInterval(() => {
            this.songProgress += 0.1;
        }, 100);
    }

    private finishAd = (): void => {
        this.currentSong.duration = 30;
        this.adPlaying = false;
        this.stopSongTimer();
        this.playNext();
    }

    private playAd(): void {
        const adSong = new Song();
        adSong.duration = 15;
        adSong.title = 'Musio Go Premium Ad';
        adSong.album = new Album();
        adSong.album.title = 'Musio Ads';
        adSong.artist = new Artist();
        adSong.artist.name = 'Musio';

        this.audio = this.makeAdHowl();
        this.adPlaying = true;
    }

    // UI Event Handlers -------------------------------------------------------------------

    private play(): void {
        if (this.audio) {
            this.audio.play();
            this.isPlaying = true;
        } else if (this.songQueue.length !== 0) {
            this.playNext();
        }
    }

    private pause(): void {
        if (this.audio) {
            this.audio.pause();
            this.isPlaying = false;
        }
    }

    private playNext = (): void => {
        if (!this.currentSong && this.songQueue.length === 0 || this.adPlaying) {
            return;
        }

        if (this.audio) {
            this.audio.pause();
        }

        this.addSongToHistory(this.currentSong);

        // Play ad after every 5th song if not premium
        if (!this.isPremiumUser) {
            this.songCount += 1;
            if (this.songCount === AudioPlayerComponent.SONGS_PER_AD) {
                this.songCount = 0;
                this.playAd();
                return;
            }
        }

        // Find next song to play
        let nextSong: Song;
        if (this.isRepeating) {
            nextSong = this.currentSong;
        } else if (this.songQueue.length !== 0) {
            if (this.isShuffling) {
                const nextIndex = Math.floor(Math.random() * this.songQueue.length);
                nextSong = this.songQueue[nextIndex];
            } else {
                nextSong = this.songQueue[0];
            }
        }

        // Play next song if exists
        if (nextSong) {
            this.removeSongFromQueue(nextSong.id);
            this._playSong(nextSong.id);
        } else {
            this.isPlaying = false;
            this.audio = null;
            this.currentSong = null;
        }
    }

    private playLast(): void {
        if (this.adPlaying) {
            return;
        }

        // If current song is in progress, restart
        if (this.audio && this.audio.seek() > AudioPlayerComponent.IN_PROGRESS_TIME) {
            this.songProgress = 0;
            this.audio.seek(0);
            return;
        }

        // Find last song
        const lastSong: Song = this.history.pop();

        // Add current song to songQueue
        this.songQueue.unshift(this.currentSong);

        if (this.audio) {
            this.audio.pause();
        }

        if (lastSong) {
            this._playSong(lastSong.id);
        }
    }

    private muteOrUnmute(): void {
        this.isMuted = !this.isMuted;
        if (this.audio) {
            this.audio.mute(this.isMuted);
        }
    }

    private adjustVolume(): void {
        if (this.audio) {
            this.audio.volume(this.volume);
        }
    }

    private repeat(): void {
        if (this.audio) {
            this.isRepeating = !this.isRepeating;
            // this.audio.loop(this.isRepeating);
        }
    }

    private shuffle(): void {
        this.isShuffling = !this.isShuffling;
    }

    private favoriteCurrentSong(): void {
        if (this.currentSong) {
            this.favoritesService.addFavoriteSongById(this.currentSong.id)
                .subscribe((success: boolean) => {
                    this.isFavorited = success;
                },
                (error: any) => {
                    console.log('error favoriting current song');
                });
        }
    }

    private showLyrics(): void {
        this.isShowingLyrics = !this.isShowingLyrics;
    }

    private seek(): void {
        if (this.adPlaying) {
            return;
        }

        this.audio.seek(this.songProgress);
        this.prevSongProgress = this.songProgress;
    }

    private qToggle(): void {
        if (this.lyricsToggle) {
            this.lyricsToggle = false;
        }
        if (!this.queueToggle) {
            this.queueTableData = new MatTableDataSource(this.songQueue);
            this.queueTableData.sort = this.sort;
        }

        this.queueToggle = !this.queueToggle;
        console.log(this.songQueue);
    }

    private lToggle(): void {
        if (this.queueToggle) {
            this.queueToggle = false;
        }
        this.lyricsToggle = !this.lyricsToggle;
    }

    private removeSongFromQueue(songId: number): void {
        for (let i = 0; i < this.songQueue.length; i += 1) {
            if (this.songQueue[i].id === songId) {
                this.songQueue.splice(i, 1);
                this.queueTableData = new MatTableDataSource(this.songQueue);
            }
        }
    }

    private unfavoriteCurrentSong(): void {
        if (this.currentSong) {
            this.favoritesService.removeFavoriteSongById(this.currentSong.id)
                .subscribe((success: boolean) => {
                    this.isFavorited = !success;
                },
                (error: any) => {
                    console.log('error favoriting current song');
                });
        }
    }

    private hoverFavorite(isHovered: boolean): void {
        this.isHovered = isHovered;
    }

    private getFavoritedIcon(): Object {
        return (this.isHovered) ? 'remove' : 'favorite';
    }
}
