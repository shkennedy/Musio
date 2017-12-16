import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AudioPlayerComponent } from './audio-player/audio-player.component';
import { TopNavComponent } from './top-nav/top-nav.component';
import { LeftNavComponent } from './left-nav/left-nav.component';
import { FollowedUsersBarComponent } from './followed-users-bar/followed-users-bar.component';

@Component({
    selector: 'app-content-home',
    templateUrl: './content-home.component.html',
    styleUrls: ['./content-home.component.css']
})
export class ContentHomeComponent implements OnInit {
    basic = true;
    constructor(
        private router: Router
    ) {
        setTimeout(() => { this.basic = false; } , 2000);
     }

    ngOnInit() {}
}
