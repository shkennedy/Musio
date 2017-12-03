import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { PaymentInfo } from '../models/paymentInfo.model';
import { User } from '../models/user.model';
import { Role } from '../models/role.model';

@Injectable()
export class UserService {

    private static USER_URL = '/user';
    private static GET_USERNAME_URL = UserService.USER_URL + '/getUsername';
    private static GET_USER_BY_USERNAME_URL = UserService.USER_URL + '/get';
    private static FOLLOW_URL = UserService.USER_URL + '/followUser';
    private static UNFOLLOW_URL = UserService.USER_URL + '/unfollowUser';
    private static GO_PREMIUM_URL = UserService.USER_URL + '/goPremium';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(callback: (user: User) => void): void {
        this.httpRequest.get(UserService.GET_USERNAME_URL)
            .map((usernameResponse: ApiResponse) => {
                if (usernameResponse.success) {
                    this.httpRequest.get(
                        UserService.GET_USER_BY_USERNAME_URL + usernameResponse.responseData)
                        .map((userResponse: ApiResponse) => {
                            callback(userResponse.responseData);
                        });
                }
                callback(null);
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

    public getIsPremium(callback: (b: boolean) => any): void {
        const getUserCallback: (user: User) => void = (user: User) => {
            for (let i = 0; i < user.roles.length; ++i) {
                if (user.roles[i].role === 'premiumUser') {
                    callback(true);
                }
            }
            callback(false);
        };
        this.getUser(getUserCallback);
    }

    // public getUserPaymentInfo(userId: number): PaymentInfo {
    //     return this.httpRequest.get(UserService.PAYMENT_INFO_URL + "/" + userId)
    //     .subscribe((paymentInfo: PaymentInfo) => {
    //         return paymentInfo;
    //     });
    // }
}
