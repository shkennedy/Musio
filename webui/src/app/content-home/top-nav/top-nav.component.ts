import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { FavoritesService } from '../../services/favorites.service';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service';
import { FileService } from '../../services/file.service';
import { TopBarProxyService } from '../../services/topBarProxy.service';
import { AdService } from '../../services/ad.service';
import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';

import { PremiumDialogComponent } from '../../dialogs/premium-dialog/premium-dialog/premium-dialog.component';

import { User } from '../../models/user.model';

@Component({
    selector: 'app-top-nav',
    templateUrl: './top-nav.component.html',
    styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

    private user: User;

    constructor(
        private router: Router,
        private topBarProxyService: TopBarProxyService,
        private userService: UserService,
        private loginService: LoginService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private adService: AdService,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private dialog: MatDialog
    ) {}

    ngOnInit() {
        this.userService.getUser(this.setUser);

        this.topBarProxyService.registerListeners(this.setIsPremium, this.refreshUserImage);
    }

    public setUser = (user: User): void => {
        this.user = user;
        this.user.isPremium = true;
        this.userService.getIsPremium(this.setIsPremium);
        this.refreshUserImage();
    }

    public setIsPremium = (isPremium: boolean): void => {
        this.user.isPremium = isPremium;
        this.adService.setShowAds(!isPremium);
        this.audioPlayerProxyService.setIsPremiumUser(isPremium);
    }

    public refreshUserImage = (): void => {
        const profile_picture = this.fileService.getUserImageURLById(this.user.id);
        this.userService.getHasImageById(this.user.id)
        .subscribe((hasImage: boolean) => {
            if (hasImage) {
                this.user.profileImageUrl = this.fileService.getUserImageURLById(this.user.id);
            } else {
                this.user.profileImageUrl = 'assets/images/no_artist_picture.png';
            }
        },
        (error: any) => {
            console.log(error);
        });
    }

    private search(searchQuery: string): void {
        this.router.navigate(['/search', searchQuery]);
    }

    gotoUserProfile() {
        this.router.navigate(['/profile']);
        console.log('clicked profile');
    }

    gotoSettings() {
        this.router.navigate(['/userSettings']);
    }

    gotoContact() {
        this.router.navigate(['/contact']);
        console.log('clicked contact');
    }

    goPremium() {
        const dialogRef = this.dialog.open(PremiumDialogComponent, {
            width: '800px',
            height: '500px'
        });
    }
}
