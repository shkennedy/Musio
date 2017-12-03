import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PickedFile } from 'angular-file-picker';

import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { User } from '../../../models/user.model';

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

    private user: User;

    private errorMessage: string;

    constructor(
        private router: Router,
        private fileService: FileService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    public setUser(user: User): void {
        this.user = user;
    }

    private changeProfileImage(profileImage: PickedFile): void {
        // this.userService.setProfileImage(profileImage)
        // .subscribe((success: boolean) => {
        // });
    }

    private changePassword(): void {

    }

    private setPrivateSession(): void {

    }

    private setPublicSession(): void {

    }
}
