import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { FilePickerModule } from 'angular-file-picker';

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
        this.userService.getUser()
        .subscribe(
            (user: User) => {
                this.user = user;
            },
            (error: any) => {
                this.errorMessage = error;
                console.log(error);
            }
        );
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
