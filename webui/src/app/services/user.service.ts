import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { PaymentInfo } from '../models/paymentInfo.model';
import { User } from '../models/user.model';

@Injectable()
export class UserService {

    private static USER_URL: string         = '/whoami';
    private static FOLLOW_URL: string       = '/followUser';
    private static UNFOLLOW_URL: string     = '/unfollowUser';
    private static GO_PREMIUM_URL: string = UserService.USER_URL + '/goPremium';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(): Observable<User> {
        return this.httpRequest.get(UserService.USER_URL)
        .map((response: ApiResponse) => {
            if (response.success) {
                return response.responseData;
            }
            return null;
        });
    }

    public followUser(userId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.FOLLOW_URL, userId)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public unfollowUser(userId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.UNFOLLOW_URL, userId)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    public goPremium(paymentInfo: PaymentInfo): Observable<boolean> {
        return this.httpRequest.post(UserService.GO_PREMIUM_URL, paymentInfo)
        .map((response: ApiResponse) => {
            return response.success;
        });
    }

    // public getIsPremium(): Observable<boolean> {
    //     return this.getUser()
    //     .map((user: User) => {
    //         return user.isPremium;
    //     });
    // }

    // public getUserPaymentInfo(userId: number): PaymentInfo {
    //     return this.httpRequest.get(UserService.PAYMENT_INFO_URL + "/" + userId)
    //     .subscribe((paymentInfo: PaymentInfo) => {
    //         return paymentInfo;
    //     });
    // }
}
