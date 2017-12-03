import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../services/user.service';

import { User } from '../../models/user.model';

@Component({
    selector: 'app-top-nav',
    templateUrl: './top-nav.component.html',
    styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

    public user: User;
    public button = true;

    constructor(
        private router: Router,
        private userService: UserService,
    ) { }

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    public setUser(user: User): void {
        this.user = user;
    }

    gotoUserProfile() {
        this.router.navigate(['/profile']);
        console.log('clicked profile');
    }

    goPremium() {
        this.router.navigate(['/goPremium']);
        console.log("clicked About");
    }

    gotoAbout() {
        this.router.navigate(['/about']);
        console.log("clicked About");
    }

    gotoContact() {
        this.router.navigate(['/contact']);
        console.log("clicked contact");
    }

}
