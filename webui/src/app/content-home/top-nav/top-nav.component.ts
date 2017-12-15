import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { FavoritesService } from '../../services/favorites.service';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service';

import { User } from '../../models/user.model';

@Component({
    selector: 'app-top-nav',
    templateUrl: './top-nav.component.html',
    styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

    private user: User = new User();

    constructor(
        private router: Router,
        private userService: UserService,
        private loginService: LoginService,
        private favoritesService: FavoritesService
    ) {}

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    public setUser = (user: User): void => {
        this.user = user;
    }

    private search(searchForm: NgForm): void {
        const searchQuery = searchForm.value['searchQuery'];
        this.router.navigate(['/search', searchQuery]);
    }

    gotoUserProfile() {
        this.router.navigate(['/profile']);
        console.log('clicked profile');
    }

    goPremium() {
        this.router.navigate(['/goPremium']);
        console.log('clicked goPremium');
    }

    gotoSettings() {
        this.router.navigate(['/userSettings']);
        // for (let i = 4; i < 100; i += 1) {
        //     console.log(i);
        //     this.favoritesService.addFavoriteAlbumById(i)
        //     .subscribe((response: boolean) => {
        //         console.log(response);
        //     });
        //     this.favoritesService.addFavoriteArtistById(i)
        //     .subscribe((response: boolean) => {
        //         console.log(response);
        //     });
        //     this.favoritesService.addFavoritePlaylistById(i)
        //     .subscribe((response: boolean) => {
        //         console.log(response);
        //     });
        //     this.favoritesService.addFavoriteSongById(i)
        //     .subscribe((response: boolean) => {
        //         console.log(response);
        //     });
        // }
    }

    gotoContact() {
        this.router.navigate(['/contact']);
        console.log('clicked contact');
    }
}
