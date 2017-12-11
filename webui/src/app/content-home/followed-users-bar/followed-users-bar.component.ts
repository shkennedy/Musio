import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../services/favorites.service';
import { UserService } from '../../services/user.service';

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
        private favoritesService: FavoritesService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.userService.getFollowedUsers()
        .subscribe(
            (users: User[]) => {
                this.users = users;
                this.userService.getFollowedUsersHistory()
                .subscribe(
                    (userHistory: Map<number, Song>) => {
                        this.userHistory = userHistory;
                    },
                    (error: any) => {
                        console.log(error.toString());
                    });
            },
            (error: any) => {
                console.log(error.toString());
            });
    }
}
