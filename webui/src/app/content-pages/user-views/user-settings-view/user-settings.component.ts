import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PickedFile } from 'angular-file-picker';
import { PACKAGE_ROOT_URL } from 'angular-file-picker/node_modules/@angular/core/src/application_tokens';
import { NgForm } from '@angular/forms';

import { AudioPlayerProxyService } from '../../../services/audioPlayerProxy.service';
import { FileService } from '../../../services/file.service';
import { UserService } from '../../../services/user.service';

import { MatDialog,
         MatDialogRef,
         MAT_DIALOG_DATA } from '@angular/material';
import { PasswordDialogComponent } from '../../../dialogs/password-dialog/password-dialog.component';
import { DeleteDialogComponent } from '../../../dialogs/delete-dialog/delete-dialog/delete-dialog.component';
import { PictureDialogComponent } from '../../../dialogs/picture-dialog/picture-dialog/picture-dialog.component';

import { User } from '../../../models/user.model';

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

    //My additions
    password_toggle:boolean=false;
    quality_toggle:boolean=false;
    session_toggle:boolean=false;
    session_type="Public Session";
    quality_name:string="Low Quality";
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

    private setAudioBitrate(): void {
        this.user.useHighBitrate = !this.user.useHighBitrate;
        this.audioPlayerProxyService.setUseHighBitrate(this.user.useHighBitrate);
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


    //my stuff

    audioToggle(data:any){
        if(data.checked){this.quality_name="High Quality";}
        else{this.quality_name="Low Quality";}
    }

    sessionToggle(data:any){
        if(data.checked){this.session_type="Private Session";}
        else{this.session_type="Public Session";}
    }

    passwordDialog(){
        let dialogRef = this.dialog.open(PasswordDialogComponent, {
          width: '800px',
          height:'500px'
        });
    }

    deleteDialog(){
        let dialogRef = this.dialog.open(DeleteDialogComponent, {
          width: '300px',
          height:'200px'
        });
    }

    pictureDialog(){
        let dialogRef = this.dialog.open(PictureDialogComponent, {
          width: '600px',
          height:'200px'
        });
    }

}
