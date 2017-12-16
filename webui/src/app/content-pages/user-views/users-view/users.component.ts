import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { User } from '../../../models/user.model';
import { Playlist } from '../../../models/playlist.model';
import { Song } from '../../../models/song.model';

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

    private user: User;
    private followedUsers: User[];
    private playlists: Playlist[];
    private history: Song[];

    private errorMessage: string;

    constructor(
        private router: Router,
        private fileService: FileService,
        private favoritesService: FavoritesService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.userService.getFollowedUsers()
        .subscribe(
            (followedUsers: User[]) => {
                this.followedUsers = followedUsers;
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error.toString());
            }
        );
    }
}
