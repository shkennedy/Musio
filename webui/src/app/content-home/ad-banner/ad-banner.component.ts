import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AdService } from '../../services/ad.service';
import { UserService } from '../../services/user.service';

import { Ad } from '../../models/ad.model';

@Component({
    selector: 'app-ad-banner',
    templateUrl: './ad-banner.component.html',
    styleUrls: ['./ad-banner.component.css']
})
export class AdBannerComponent implements OnInit {

    private showAds: boolean;
    private ads: Ad[];

    constructor(
        private router: Router,
        private userService: UserService,
        private adService: AdService
    ) { }

    ngOnInit() {
        this.userService.getIsPremium(this.setIsPremium);
    }

    private setIsPremium = (isPremium: boolean): void => {
        this.showAds = !isPremium;

        if (this.showAds) {
            this.adService.getVerticalBannerAds()
            .subscribe(
                (ads: Ad[]) => {
                    this.ads = ads;
                },
                (error: any) => {
                    console.log(error.toString());
                });
        }
    }
}