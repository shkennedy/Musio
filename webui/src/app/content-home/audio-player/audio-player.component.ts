import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Howl } from 'howler';

import { Song } from '../../models/song.model';
import { SongService } from '../../services/song.service';

@Component({
  selector: 'app-audio-player',
  templateUrl: './audio-player.component.html',
  styleUrls: ['./audio-player.component.css']
})
export class AudioPlayerComponent implements OnInit {

  private static IN_PROGRESS_TIME = 5;

  // Main
  // https://github.com/goldfire/howler.js#documentation

  // Alternatives
  // https://wiki.mozilla.org/Audio_Data_API#API_Tutorial
  // https://github.com/arielfaur/ionic-audio/tree/3.0

  model: {
    currentSong: Song,
    songQueue: Song[],
    history: Song[],
    volume: number,
    isMuted: boolean,
    isPlaying: boolean,
    isRepeating: boolean,
  };
  errMsg: string;

  private audio: Howl;

  constructor(
    private router: Router,
    private songService: SongService
  ) { }

  ngOnInit() {}

  private makeHowl(song: Song): Howl {
    const newHowl: Howl = new Howl({
      src: [song.audio],
      volume: this.model.volume,
      autoplay: true,
      onend: function () {
        this.forward();
      },
      onloaderror: function () {
        this.errMsg = 'Unable to load song';
      },
      onplayerror: function () {
        this.errMsg = 'Unable to play song';
      }
    });
  }

  public playSong(songId: number): void {
    this.songService.getSongById(4)
    .subscribe((song: Song) => {
      if (!song) {
        return;
      }
      this.audio = this.makeHowl(song);
      this.model.currentSong = song;
    });
  }

  public playNext(): void {
    const nextSong: Song = this.model.songQueue.shift();
    this.model.history.push(this.model.currentSong);
    if (nextSong) {
      this.playSong(nextSong.song_id);
    }
  }

  public playLast(): void {
    // If current song is in progress, restart
    if (this.audio.seek() > AudioPlayerComponent.IN_PROGRESS_TIME) {
      this.audio.seek(0);
      return;
    }

    // Find last song
    const lastSong: Song = this.model.history.pop();

    // Add current song to songQueue
    this.model.songQueue.push(this.model.songQueue[0]);
    this.model.songQueue[0] = this.model.currentSong;

    if (lastSong) {
      this.playSong(lastSong.song_id);
    }
  }

  public playOrPause(): void {
    if (!this.model.currentSong) {
      return;
    }

    if (this.model.isPlaying) {
      this.audio.pause();
    } else {
      this.audio.play();
    }
    this.model.isPlaying = !this.model.isPlaying;
  }
  
  public muteOrUnmute(): void {
    this.model.isMuted = !this.model.isMuted;
    this.audio.mute(this.model.isMuted);
  }

  public repeat(): void {
    this.model.isRepeating = !this.model.isRepeating;
    this.audio.loop(this.model.isRepeating);
  }
}
