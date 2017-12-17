import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { NgForm } from '@angular/forms';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-friends-dialog',
  templateUrl: './friends-dialog.component.html',
  styleUrls: ['./friends-dialog.component.css']
})
export class FriendsDialogComponent implements OnInit {

  loading = false;
  users: User[];
  constructor(public dialogRef: MatDialogRef<FriendsDialogComponent>,
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

  searchFriends(form: NgForm) {
    console.log(form.form.value);
    if (form.form.value.friend_name === '') {
        console.log('invalid search');
    }else {
        console.log('searching');
    }
    // Must grab images and if bad image set to /assets/images/no_artist_picture.png
  }

  followUser(user: User) {
    user.isFollowed = true;
  }

  unfollowUser(user: User) {
    user.isFollowed = false;
  }

}


