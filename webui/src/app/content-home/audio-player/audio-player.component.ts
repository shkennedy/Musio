import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Howler } from 'howler';

import { SongService } from '../../services/song.service';
import { FileService } from '../../services/file.service';
import { PlaylistService } from '../../services/playlist.service';
import { UserService } from '../../services/user.service';

import { Playlist } from '../../models/playlist.model';
import { Song } from '../../models/song.model';

@Component({
    selector: 'app-audio-player',
    templateUrl: './audio-player.component.html',
    styleUrls: ['./audio-player.component.css'],
    providers: [FileService, PlaylistService, SongService, UserService]
})
export class AudioPlayerComponent implements OnInit {

    // Main
    // https://www.npmjs.com/package/@types/howler
    // https://github.com/DefinitelyTyped/DefinitelyTyped/blob/master/types/howler/index.d.ts

    // Alternatives
    // https://521dimensions.com/open-source/amplitudejs
    // https://wiki.mozilla.org/Audio_Data_API#API_Tutorial
    // https://github.com/arielfaur/ionic-audio/tree/3.0

    private static IN_PROGRESS_TIME = 5;

    audio: Howl;
    useHighBitrate: boolean;
    privateMode: boolean;

    currentSong: Song;
    songQueue: Song[];
    history: Song[];
    volume: number;
    isMuted: boolean;
    isPlaying: boolean;
    isRepeating: boolean;

    errMsg: string;

    constructor(
        private router: Router,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private userService: UserService
    ) { }

    ngOnInit() {
        // Find user allowed bitrate
        const setUseHighBitrate = (useHighBitrate: boolean): void => {
            this.useHighBitrate = useHighBitrate;
        };
        this.userService.getIsPremium(setUseHighBitrate);

        this.userService.getPrivateMode()
            .subscribe(
            (privateMode: boolean) => {
                this.privateMode = privateMode;
            },
            (error: any) => {
                console.log(error.toString());
            }
            );

        this.userService.getHistory()
            .subscribe(
            (history: Song[]) => {
                this.history = history;
            },
            (error: any) => {
                console.log(error.toString());
            }
            );
    }

    public addSongToQueue(songId: number): void {
        this.songService.getSongById(songId)
            .subscribe(
            (song: Song) => {
                if (song !== null) {
                    this.songQueue.unshift(song);
                }
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    public playSong(songId: number): void {
        this.addCurrentSongToHistory();
        this.songQueue = [];
        this._playSong(songId);
    }

    public playPlaylist(playlistId: number): void {
        this.addCurrentSongToHistory();
        this.playlistService.getPlaylistById(playlistId)
            .subscribe((playlist: Playlist) => {
                if (playlist !== null) {
                    const song = playlist.songs.shift();
                    this._playSong(song.id);
                    this.songQueue = playlist.songs;
                }
            });
    }

    public setPrivateMode(privateMode: boolean): void {
        this.privateMode = privateMode;
    }

    private makeHowl(songFile: File): Howl {
        const newHowl: Howl = new Howl({
            // src: [song.audio],
            src: ['../../assets/testSong.mp3'],
            volume: this.volume,
            autoplay: true,
            onend: function () {
                if (!this.isRepeating) {
                    this.playNext();
                }
            },
            onloaderror: function () {
                this.errMsg = 'Unable to load song';
            },
            onplayerror: function () {
                this.errMsg = 'Unable to play song';
            }
        });

        return newHowl;
    }

    private _playSong(songId: number): void {
        // Fetch song object
        this.songService.getSongById(4)
            .subscribe(
            (song: Song) => {
                this.currentSong = song;
                // Fetch song file
                this.fileService.getSongFileByIdAndBitrate(song.id, this.useHighBitrate)
                    .subscribe(
                    (songFile: File) => {
                        this.audio = this.makeHowl(songFile);
                        this.isPlaying = true;
                    },
                    (error: any) => {
                        this.isPlaying = false;
                        this.audio = null;
                        this.currentSong = null;
                        console.log(error.toString());
                    }
                    );
            },
            (error: any) => {
                console.log(error.toString());
            }
            );
    }

    private playNext(): void {
        this.addCurrentSongToHistory();
        const nextSong: Song = this.songQueue.shift();
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
        if (this.audio.seek() > AudioPlayerComponent.IN_PROGRESS_TIME) {
            this.audio.seek(0);
            return;
        }

        // Find last song
        const lastSong: Song = this.history.pop();

        // Add current song to songQueue
        this.songQueue.unshift(this.currentSong);

        if (lastSong) {
            this._playSong(lastSong.id);
        }
    }

    private play(): void {
        if (this.audio !== null) {
            this.audio.play();
            this.isPlaying = true;
        }
    }

    private pause(): void {
        if (this.audio !== null) {
            this.audio.pause();
            this.isPlaying = false;
        }
    }

    private muteOrUnmute(): void {
        this.isMuted = !this.isMuted;
        this.audio.mute(this.isMuted);
    }

    private repeat(): void {
        this.isRepeating = !this.isRepeating;
        this.audio.loop(this.isRepeating);
    }

    private addCurrentSongToHistory(): void {
        if (this.currentSong === null) {
            return;
        }

        this.history.push(this.currentSong);
        if (!this.privateMode) {
            this.userService.addSongIdToHistory(this.currentSong.id);
        }
    }
}
