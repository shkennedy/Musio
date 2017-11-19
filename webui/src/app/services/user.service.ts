import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService } from './httpRequest.service';

import { PaymentInfo } from '../models/paymentInfo.model';
import { User } from '../models/user.model';

@Injectable()
export class UserService {

    private static USER_URL: string         = '/user';
    private static FOLLOW_URL: string       = '/followUser';
    private static UNFOLLOW_URL: string     = '/unfollowUser';
    private static PAYMENT_INFO_URL: string = UserService.USER_URL + '/paymentInfo';

    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(): User {
        let id: number = UserInfoService.getUserId();
        return this.httpRequest.get(UserService.USER_URL, {userId: id})
        .subscribe((user: User) => {
            return user;
        });
    }

    public followUser(id: number): boolean {
        return this.httpRequest.get(UserService.FOLLOW_URL, {userId: id})
        .subscribe((success: boolean) => {
            return success;
        });
    }

    public unfollowUser(id: number): boolean {
        return this.httpRequest.get(UserService.UNFOLLOW_URL, {userId: id})
        .subscribe((success: boolean) => {
            return success;
        });
    }
    
    public getUserPaymentInfo(userId: number): PaymentInfo {
        return this.httpRequest.get(UserService.PAYMENT_INFO_URL + "/" + userId)
        .subscribe((paymentInfo: PaymentInfo) => {
            return paymentInfo;
        });
    }
}	
