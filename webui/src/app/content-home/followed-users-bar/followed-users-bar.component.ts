import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../services/favorites.service';
import { UserService } from '../../services/user.service';
import { FollowedUsersBarProxyService } from '../../services/followedUsersBarProxy.service';
import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FriendsDialogComponent } from '../../dialogs/friends-dialog/friends-dialog/friends-dialog.component';

import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { User } from '../../models/user.model';
import { Song } from '../../models/song.model';

@Component({
    selector: 'app-followed-users-bar',
    templateUrl: './followed-users-bar.component.html',
    styleUrls: ['./followed-users-bar.component.css']
})
export class FollowedUsersBarComponent implements OnInit {

    private users: User[];
    private userHistory = new Map<number, Song>();

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private followedUsersBarProxyService: FollowedUsersBarProxyService,
        private favoritesService: FavoritesService,
        private userService: UserService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private dialog: MatDialog
    ) { }

    ngOnInit() {
        this.followedUsersBarProxyService.registerListeners(this.refreshFollowedUsers);
        setInterval(this.getFollowedUsersHistory, 25000);
        this.refreshFollowedUsers();
    }

    private refreshFollowedUsers = (): void => {
        this.userService.getFollowedUsers()
        .subscribe(
            (users: User[]) => {
                this.users = users;
                this.getFollowedUsersHistory();
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    private getFollowedUsersHistory = (): void => {
        this.userService.getFollowedUsersHistory()
        .subscribe(
            (userHistory: Map<number, Song>) => {
                console.log(userHistory);
                this.userHistory = userHistory;
            },
            (error: any) => {
                console.log(error.toString());
            });
    }

    findFriends() {
        const dialogRef = this.dialog.open(FriendsDialogComponent, {
            width: '400px',
            height: '400px'
        });
    }

    playSong(song: Song) {
        this.audioPlayerProxyService.playSong(song.id);
    }
}
