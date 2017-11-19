import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { SessionService } from './session.service';

// import { PaymentInfo } from '../models/paymentInfo.model';
import { User } from '../models/user.model';

@Injectable()
export class UserService {

    private static USER_URL: string         = '/user';
    private static FOLLOW_URL: string       = '/followUser';
    private static UNFOLLOW_URL: string     = '/unfollowUser';
    private static PAYMENT_INFO_URL: string = UserService.USER_URL + '/paymentInfo';

    constructor(
        private router: Router,
        private sessionService: SessionService,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(): Observable<User> {
        let userId: number = this.sessionService.getUserId();
        return this.httpRequest.get(UserService.USER_URL, {'userId': userId})
        .subscribe((response: ApiResponse) => {
            if (response.success) {
                return response.data;
            }
            return null;
        });
    }

    public followUser(userId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.FOLLOW_URL, {'userId': userId})
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }

    public unfollowUser(userId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.UNFOLLOW_URL, {'userId': userId})
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }
    
    // public getUserPaymentInfo(userId: number): PaymentInfo {
    //     return this.httpRequest.get(UserService.PAYMENT_INFO_URL + "/" + userId)
    //     .subscribe((paymentInfo: PaymentInfo) => {
    //         return paymentInfo;
    //     });
    // }

    /**
	 * Re-query the user stored in the session from the database.
	 */
	// @RequestMapping(value={"/refreshCurrentUser"}, method = RequestMethod.GET)
	// public @ResponseBody boolean refreshUser(HttpSession session) {
	// 	User user = (User) session.getAttribute("user");
	// 	session.setAttribute("user", userService.findUserByUsername(user.getUsername()));
	// 	return true;
	// }
}	
