import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FileService } from '../../../services/file.service';
import { PlaylistService } from '../../../services/playlist.service';
import { UserService } from '../../../services/user.service';

import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';
import { User } from '../../../models/user.model';

@Component({
    selector: 'app-playlist-view',
    templateUrl: './playlist-view.component.html',
    styleUrls: ['./playlist-view.component.css']
})
export class PlaylistViewComponent implements OnInit {

    @Input() playlistId: number;

    private playlist: Playlist;
    private isOwner: boolean;
    private isEditable: boolean;
    private titleSort: boolean;
    private ascendingOrder: boolean;

    constructor(
        private router: Router,
        private fileService: FileService,
        private playlistService: PlaylistService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.playlistService.getPlaylistById(this.playlistId)
            .subscribe(
                (playlist: Playlist) => {
                    this.playlist = playlist;
                    this.isEditable = this.playlist.isCollaborative;
                    this.userService.getUser(this.getIsOwner);
                    // this.playlist.playlistImageUrl = TODO
                    //     this.fileService.getPlaylistImageByIdAndSize(this.playlist.id, true);
                },
                (error: any) => {
                    console.log(error.toString());
                });
    }

    private getIsOwner = (user: User): void => {
        this.isOwner = this.playlist.owner.id === user.id;
        if (this.isOwner) {
            this.isEditable = true;
        }
    }

    private sort(type: string): void {
        if (type === 'title') {
            if (this.titleSort) {
                this.ascendingOrder = !this.ascendingOrder;
            } else {
                this.titleSort = true;
            }
            this.playlistService.sortByTitle(this.playlist, this.ascendingOrder);
        } else {
            if (!this.titleSort) {
                this.ascendingOrder = !this.ascendingOrder;
            } else {
                this.titleSort = false;
            }
            this.playlistService.sortByTrack(this.playlist, this.ascendingOrder);
        }
    }

    private renamePlaylist(): void {
        if (this.isEditable) {
            this.playlistService.updatePlaylist(this.playlist);
        }
    }

    private deletePlaylist(): void {
        if (this.isOwner) {
            this.playlistService.deletePlaylist(this.playlist.id);
        }
    }
}
