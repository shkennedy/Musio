import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdService } from '../services/ad.service';

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

    basic = false;
    closed_ad = false;

    constructor(
        private router: Router,
        private adService: AdService
    ) {}

    ngOnInit() {
        this.adService.registerCloseAd(this.closeAdCallback);
        this.adService.registerSetShowAdsListener(this.setShowAds);
    }

    contentSize() {
        return (this.basic && !this.closed_ad) ? 'ad_size' : 'no_ad_size';
    }

    isAdOpen() {
        return (this.basic && !this.closed_ad) ? 'block' : 'none';
    }

    public closeAdCallback = (): void => {
        this.closed_ad = true;
        setTimeout( () => { this.closed_ad = false; } , 120000);
        // setTimeout( () => { this.closed_ad = false; } , 3000);
    }

    public setShowAds = (showAds: boolean): void => {
        this.basic = showAds;
    }
}
