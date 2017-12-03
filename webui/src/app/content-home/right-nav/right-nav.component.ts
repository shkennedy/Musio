import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AdService } from '../../services/ad.service';
import { UserService } from '../../services/user.service';

import { Ad } from '../../models/ad.model';

@Component({
    selector: 'app-right-nav',
    templateUrl: './right-nav.component.html',
    styleUrls: ['./right-nav.component.css']
})
export class RightNavComponent implements OnInit {

    private showAds: boolean;
    private ads: Ad[];

    constructor(
        private router: Router,
        private userService: UserService,
        private adService: AdService
    ) { }

    ngOnInit() {
        // this.userService.getIsPremium()
        // .subscribe(
        //     (isPremium: boolean) => {
        //         this.showAds = !isPremium;
        //     },
        //     (error: any) => {
        //         console.log(error.toString());
        //     }
        // )

        if (this.showAds) {
            this.adService.getVerticalBannerAds()
            .subscribe(
                (ads: Ad[]) => {
                    this.ads = ads;
                },
                (error: any) => {
                    console.log(error.toString());
                }
            )
        }
    }

}
