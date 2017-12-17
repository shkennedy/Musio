import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PickedFile } from 'angular-file-picker';
import { PACKAGE_ROOT_URL } from 'angular-file-picker/node_modules/@angular/core/src/application_tokens';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { PasswordDialogComponent } from '../../../dialogs/password-dialog/password-dialog.component';
import { DeleteDialogComponent } from '../../../dialogs/delete-dialog/delete-dialog/delete-dialog.component';
import { PictureDialogComponent } from '../../../dialogs/picture-dialog/picture-dialog/picture-dialog.component';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { Role } from '../../../models/role.model';
import { User } from '../../../models/user.model';

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

    private user: User;
    private isPremiumUser = false;
    private password: string;
    private securityCode: number;

    private errorMessage: string;

    password_toggle = false;
    quality_toggle = false;
    session_toggle = false;
    session_type = 'Public Session';
    quality_name = 'Low Quality';

    constructor(
        private router: Router,
        private dialog: MatDialog,
        private fileService: FileService,
        private userService: UserService,
        private audioPlayerProxyService: AudioPlayerProxyService
    ) { }

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    public setUser = (user: User): void => {
        this.user = user;
        user.roles.forEach((role: Role) => {
            if (role.role === 'PREMIUM_USER') {
                this.isPremiumUser = true;
            }
        });
    }

    private changeProfileImage(profileImage: PickedFile): void {
        // this.userService.setProfileImage(profileImage)
        // .subscribe((success: boolean) => {
        // });
    }

    private setPrivateSession(): void {
        this.user.privateSession = !this.user.privateSession;
        this.userService.setPrivateSession(this.user.privateSession);
        this.audioPlayerProxyService.setPrivateSession(this.user.privateSession);
    }

    private deleteAccount(): void {
        // Confirmation dialog

        // Pleading/Survey of use

    }

    private audioToggle(data: any): void {
        console.log(data.checked);
        this.user.useHighBitrate = data.checked;
        this.audioPlayerProxyService.setUseHighBitrate(this.user.useHighBitrate);

        if (data.checked) {
            this.quality_name = 'High Quality';
        } else {
            this.quality_name = 'Low Quality';
        }
    }

    private sessionToggle(data: any): void {
        this.user.privateSession = data.checked;
        this.audioPlayerProxyService.setPrivateSession(this.user.privateSession);
        this.userService.setPrivateSession(this.user.privateSession)
        .subscribe((success: boolean) => {},
        (error: any) => {
            console.log(error);
        });

        if (data.checked) {
            this.session_type = 'Private Session';
        } else {
            this.session_type = 'Public Session';
        }
    }

    private passwordDialog(): void {
        const dialogRef = this.dialog.open(PasswordDialogComponent, {
            width: '400px',
            height: '250px'
        });
    }

    deleteDialog() {
        const dialogRef = this.dialog.open(DeleteDialogComponent, {
            width: '300px',
            height: '200px'
        });
    }

    pictureDialog() {
        const dialogRef = this.dialog.open(PictureDialogComponent, {
            width: '500px',
            height: '150px'
        });
    }
}
