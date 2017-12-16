import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Ad } from '../models/ad.model';

@Injectable()
export class AdService {

    private static AD_URL = '/ad';
    private static AD_CLICK_URL: string = AdService.AD_URL + '/click';
    private static AUDIO_AD_URLS = ['../../assets/audio/ad.ogg', '../../assets/audio/ad2.ogg'];

    private lastListenedAd = 0;

    private closeAdListener: () => void;

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getVerticalBannerAds(): Observable<Ad[]> {
        return this.httpRequest.get(AdService.AD_URL)
        .map((response: ApiResponse) => {
            return response.responseData;
        });
    }

    public recordAdClickById(adId: number): void {
        this.httpRequest.put(AdService.AD_CLICK_URL, adId);
    }

    public registerCloseAd(closeAdListener: () => void): void {
        this.closeAdListener = closeAdListener;
    }

    public closeAd(): void {
        this.closeAdListener();
    }

    public getAudioAdUrl(): string {
        if (this.lastListenedAd === AdService.AUDIO_AD_URLS.length - 1) {
            this.lastListenedAd = 0;
        } else {
            this.lastListenedAd += 1;
        }

        return AdService.AUDIO_AD_URLS[this.lastListenedAd];
    }
}
