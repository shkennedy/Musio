import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PickedFile } from 'angular-file-picker';

import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { User } from '../../../models/user.model';
import { PACKAGE_ROOT_URL } from 'angular-file-picker/node_modules/@angular/core/src/application_tokens';

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

    private user: User;
    private password: string;
    private securityCode: number;

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

    private sendChangePasswordEmail(): void {
        this.userService.sendChangePasswordEmail(this.user.id)
        .subscribe(
            (success: boolean) => {
            // Show security code dialog TODO
            },
            (error: any) => {
                this.errorMessage = error.toString();
            });
    }

    private changePassword(): void {
        this.userService.changePassword(this.user.id, this.securityCode, this.password)
        .subscribe(
            (success: boolean) => {
            // Show success message
            },
            (error: any) => {
                this.errorMessage = error.toString();
            });
    }

    private setPrivateSession(): void {

    }

    private setPublicSession(): void {

    }

    private deleteAccount(): void {
        // Confirmation dialog

        // Pleading/Survey of use
        
    }
}
