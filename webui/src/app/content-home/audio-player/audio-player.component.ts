import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Howl } from 'howler';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { AlbumService } from '../../services/album.service';
import { ArtistService } from '../../services/artist.service';
import { SongService } from '../../services/song.service';
import { FileService } from '../../services/file.service';
import { PlaylistService } from '../../services/playlist.service';
import { UserService } from '../../services/user.service';

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

    private static IN_PROGRESS_TIME = 1;

    audio: Howl;
    useHighBitrate: boolean;
    volume = 1.0;
    isMuted = false;
    isPlaying = false;
    isRepeating = false;
    isShuffling = false;
    isShowingLyrics = false;
    songProgress = 0;
    prevSongProgress = 0;
    songTimer: any;
    currentSong: Song;
    songQueue: Song[] = [];
    history: Song[] = [];
    privateMode: boolean;

    errMsg: string;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private albumService: AlbumService,
        private artistService: ArtistService,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private userService: UserService
    ) { }

    ngOnInit() {
        // Register listeners for external callers
        this.audioPlayerProxyService.registerListeners(
            this.addAlbumToQueue, this.playAlbum,
            this.addArtistToQueue, this.playArtist,
            this.addPlaylistToQueue, this.playPlaylist,
            this.addSongToQueue, this.playSong,
            this.setPrivateMode, this.setUseHighBitrate
        );

        // Find user allowed bitrate
        this.userService.getIsPremium(this.setUseHighBitrate);

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
                    this.songQueue.push(song);
                    console.log(`${this.songQueue}`);
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
                        this.songQueue.unshift(song);
                    });
                }
            });
    }

    public playPlaylist = (playlistId: number): void => {
        this.addSongToHistory(this.currentSong);
        this.playlistService.getPlaylistById(playlistId)
            .subscribe((playlist: Playlist) => {
                if (playlist) {
                    const song = playlist.songs.shift();
                    this._playSong(song.id);
                    playlist.songs.reverse().forEach((playlistSong: Song) => {
                        this.songQueue.unshift(playlistSong);
                    });
                }
            });
    }

    public addAlbumToQueue = (albumId: number): void => {
        this.albumService.getAlbumById(albumId)
            .subscribe((album: Album) => {
                if (album) {
                    album.songs.forEach((song: Song) => {
                        this.songQueue.unshift(song);
                    });
                }
            });
    }

    public playAlbum = (albumId: number): void => {
        this.addSongToHistory(this.currentSong);
        this.albumService.getAlbumById(albumId)
            .subscribe((album: Album) => {
                if (album) {
                    const song = album.songs.shift();
                    this._playSong(song.id);
                    album.songs.reverse().forEach((playlistSong: Song) => {
                        this.songQueue.unshift(playlistSong);
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
                });
                allAlbumSongs.reverse().forEach((playlistSong: Song) => {
                    this.songQueue.unshift(playlistSong);
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
        // Fetch song object
        this.songService.getSongById(songId)
            .subscribe(
            (song: Song) => {
                this.currentSong = song;
                // Convert duration to seconds from ms
                // this.currentSong.duration = this.currentSong.duration / 1000;
                this.currentSong.duration = 30;
                console.log(`song: ${this.currentSong.title} duration: ${this.currentSong.duration}`);
                // Fetch song file
                this.currentSong.audioFileUrl = this.fileService
                    .getSongFileURLByIdAndBitrate(song.id, this.useHighBitrate);
                this.audio = this.makeHowl(this.currentSong.audioFileUrl);
                this.songProgress = 0;
                this.prevSongProgress = 0;
                this.isPlaying = true;
            },
            (error: any) => {
                this.isPlaying = false;
                this.audio = null;
                this.currentSong = null;
                console.log('something went wrong ' + error.toString());
            }
            );
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
                    console.log(`added ${song.id} to history`);
                });
        }
    }

    // UI Event Handlers -------------------------------------------------------------------

    private play(): void {
        console.log(this.songQueue);
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
        if (!this.currentSong && this.songQueue.length === 0) {
            return;
        }

        if (this.audio) {
            this.audio.pause();
        }

        this.addSongToHistory(this.currentSong);

        let nextSong: Song;
        if (this.isRepeating) {
            nextSong = this.currentSong;
        } else if (this.songQueue.length !== 0) {
            if (this.isShuffling) {
                const nextIndex = Math.floor(Math.random() * this.songQueue.length);
                nextSong = this.songQueue.splice(nextIndex, 1)[0];
            } else {
                nextSong = this.songQueue.shift();
            }
        }

        if (nextSong) {
            this._playSong(nextSong.id);
        } else {
            this.isPlaying = false;
            this.audio = null;
            this.currentSong = null;
        }
    }

    private playLast(): void {
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

    private showLyrics(): void {
        this.isShowingLyrics = !this.isShowingLyrics;
    }

    private seek(): void {
        this.audio.seek(this.songProgress);
        this.prevSongProgress = this.songProgress;
    }

    private sortQueue(): void {
        // if (this.ascendingOrder) {
        //     this.songQueue.sort(())
        // }
        // sortAlbumByTrack(album: Album, ascending: boolean): void {
        //     if (ascending) {
        //         album.songs.sort((a: Song, b: Song) => {
        //             if (a.trackNumber > b.trackNumber) {
        //                 return 1;
        //             }
        //             return -1;
        //         });
        //     } else {
        //         album.songs.sort((a: Song, b: Song) => {
        //             if (a.trackNumber < b.trackNumber) {
        //                 return 1;
        //             }
        //             return -1;
        //         });
        //     }
        // }
    }
}
