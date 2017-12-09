import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FileService } from '../../../services/file.service';
import { PlaylistService } from '../../../services/playlist.service';

import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-playlist-view',
    templateUrl: './playlist-view.component.html',
    styleUrls: ['./playlist-view.component.css']
})
export class PlaylistViewComponent implements OnInit {

    @Input() playlistId: number;

    private playlist: Playlist;

    constructor(
        private router: Router,
        private fileService: FileService,
        private playlistService: PlaylistService
    ) { }

    ngOnInit() {
        this.playlistService.getPlaylistById(this.playlistId)
            .subscribe(
                (playlist: Playlist) => {
                    this.playlist = playlist;
                    // this.playlist.playlistImageUrl = TODO
                    //     this.fileService.getPlaylistImageByIdAndSize(this.playlist.id, true);
                },
                (error: any) => {
                    console.log(error.toString());
                }
        );
    }
}
