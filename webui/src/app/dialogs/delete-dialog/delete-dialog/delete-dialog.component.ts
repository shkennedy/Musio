import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { UserService } from '../../../services/user.service';

import { User } from '../../../models/user.model';

@Component({
    selector: 'app-delete-dialog',
    templateUrl: './delete-dialog.component.html',
    styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent implements OnInit {

    private user: User;

    constructor(
        private userService: UserService,
        public dialogRef: MatDialogRef<DeleteDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) { }

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    setUser = (user: User): void => {
        this.user = user;
    }

    deleteAccount() {
        console.log('trying delete');
        this.userService.deleteUser(this.user.id)
            .subscribe((success: boolean) => {
                console.log('succeeded');
                window.location.href = '/logout';
            });
    }
}
