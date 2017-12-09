import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { UserService } from '../../services/user.service';

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
    ) { }

    ngOnInit() {
        this.userService.getUser(this.setUser);
    }

    public setUser(user: User): void {
        console.log(`whats good ${user}`);
        this.user = user;
    }

    private search(searchForm: NgForm): void {
        const searchQuery = searchForm.value['searchQuery'];
        console.log(searchQuery);
        this.router.navigate(['/search', 'frank']);
    }

    gotoUserProfile() {
        this.router.navigate(['/profile']);
        console.log('clicked profile');
    }

    goPremium() {
        this.router.navigate(['/goPremium']);
        console.log('clicked goPremium');
    }

    gotoAbout() {
        this.router.navigate(['/about']);
        console.log('clicked About');
    }

    gotoContact() {
        this.router.navigate(['/contact']);
        console.log('clicked contact');
    }

}
