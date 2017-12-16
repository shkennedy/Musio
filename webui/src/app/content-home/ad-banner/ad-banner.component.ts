import { Component, OnInit, OnDestroy} from '@angular/core';
import { Router } from '@angular/router';

import { AdService } from '../../services/ad.service';
import { UserService } from '../../services/user.service';
import { FileService } from '../../services/file.service';

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
        private adService: AdService,
        private fileService: FileService
    ) { }

    ngOnInit() {
        this.userService.getIsPremium(this.setIsPremium);
    }

    private setIsPremium = (isPremium: boolean): void => {
        this.showAds = !isPremium;

        if (this.showAds) {
            // this.adService.getRandomAd()
            // .subscribe((response: any) => {
            //     console.log('inc');
            //     console.log(response);
            // },
            // (error: any) => {
            //     console.log('inc');
            //     console.log(error);
            // });

            // this.adService.getVerticalBannerAds()
            // .subscribe(
            //     (ads: Ad[]) => {
            //         this.ads = ads;
            //     },
            //     (error: any) => {
            //         console.log(error.toString());
            //     });
        }
    }

    private closeAd(): void {
        console.log('clicked');
        this.adService.closeAd();
    }
}
