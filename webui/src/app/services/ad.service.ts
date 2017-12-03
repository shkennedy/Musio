import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Ad } from '../models/ad.model';

@Injectable()
export class AdService {

    private static AD_URL = '/ad';
    private static AD_CLICK_URL: string = AdService.AD_URL + '/click';

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
}
