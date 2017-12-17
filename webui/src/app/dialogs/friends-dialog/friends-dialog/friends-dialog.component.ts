import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { FileService } from '../../../services/file.service';

@Component({
    selector: 'app-friends-dialog',
    templateUrl: './friends-dialog.component.html',
    styleUrls: ['./friends-dialog.component.css']
})
export class FriendsDialogComponent implements OnInit {

    loading = false;
    users: User[];
    constructor(
        private userService: UserService,
        private fileService: FileService,
        public dialogRef: MatDialogRef<FriendsDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) { }

    ngOnInit() {
        //   const u = new User();
        //   u.username = 'username';
        //   u.profileImageUrl = '/assets/images/no_artist_picture.png';
        //   const u2 = new User();
        //   u2.username = 'username';
        //   u2.profileImageUrl = '/assets/images/no_artist_picture.png';
        //   this.users = [ u , u2 ];
    }

    searchFriends(username: string) {
        if (username !== '') {
            console.log('searching');
            this.userService.searchUsersByUsername(username)
                .subscribe((users: User[]) => {
                    this.users = users;

                    this.userService.getFollowedUsers()
                        .subscribe((followedUsers: User[]) => {
                            this.users.forEach((user: User) => {
                                user.isFollowed = false;
                                followedUsers.forEach((followedUser: User) => {
                                    if (user.id === followedUser.id) {
                                        user.isFollowed = true;
                                    }
                                });
                            });
                        },
                        (error: any) => {
                            console.log(error);
                        });

                    // Set user profile image to default url if none exists
                    this.users.forEach((user: User) => {
                        this.userService.getHasImageById(user.id)
                            .subscribe((hasImage: boolean) => {
                                if (hasImage) {
                                    user.profileImageUrl = this.fileService.getUserImageURLById(user.id);
                                } else {
                                    user.profileImageUrl = '/assets/images/no_artist_picture.png';
                                }
                            });
                    });
                });
        }
    }

    followUser(user: User) {
        this.userService.followUser(user.id)
            .subscribe((success: boolean) => {
                user.isFollowed = true;
            });
    }

    unfollowUser(user: User) {
        this.userService.unfollowUser(user.id)
            .subscribe((success: boolean) => {
                user.isFollowed = false;
            });
    }
}


