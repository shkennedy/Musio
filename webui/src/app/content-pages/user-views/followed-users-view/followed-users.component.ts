import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FavoritesService } from '../../../services/favorites.service';
import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { User } from '../../../models/user.model';

@Component({
    selector: 'app-followed-users',
    templateUrl: './followed-users.component.html',
    styleUrls: ['./followed-users.component.css']
})
export class FollowedUsersComponent implements OnInit {

    private followedUsers: User[];

    private errorMessage: string;

    constructor(
        private router: Router,
        private fileService: FileService,
        private favoritesService: FavoritesService,
        private userService: UserService
    ) { }

    ngOnInit() {
        // this.userService.getFollowedUsers()
        // .subscribe(
        //     (followedUsers: User[]) => {
        //         this.followedUsers = followedUsers;
        //     },
        //     (error: any) => {
        //         this.errorMessage = error;
        //         console.log(error.toString());
        //     }
        // );
    }

    changeProfileImage() {

    }

    changePassword() {

    }

    setPrivateSession() {

    }

    setPublicSession() {

    }
}
