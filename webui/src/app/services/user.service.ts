import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { PaymentInfo } from '../models/paymentInfo.model';
import { User } from '../models/user.model';
import { Role } from '../models/role.model';
import { Song } from '../models/song.model';

@Injectable()
export class UserService {

    private static USER_URL = '/user';
    private static GET_USER_URL = UserService.USER_URL + '/getUser';
    private static GET_USERNAME_URL = UserService.USER_URL + '/getUsername';
    private static GET_USER_BY_USERNAME_URL = UserService.USER_URL + '/get';
    private static FOLLOWED_USERS_URL = UserService.USER_URL + '/followedUsers';
    private static FOLLOWED_USERS_HISTORY = UserService.FOLLOWED_USERS_URL + '/history';
    private static FOLLOW_URL = UserService.USER_URL + '/followUser';
    private static UNFOLLOW_URL = UserService.USER_URL + '/unfollowUser';
    private static GO_PREMIUM_URL = UserService.USER_URL + '/goPremium';
    private static BROWSING_MODE_URL = UserService.USER_URL + '/getBrowsingMode';
    private static DISABLE_PRIVATE_MODE_URL = UserService.USER_URL + '/disablePrivateMode';
    private static ENABLE_PRIVATE_MODE_URL = UserService.USER_URL + '/enablePrivateMode';
    private static LISTENING_HISTORY_URL = UserService.USER_URL + '/myListeningHistory';
    private static HISTORY_BY_ID_URL = UserService.USER_URL + '/listeningHistoryForUser';
    private static ADD_TO_HISTORY_URL = UserService.USER_URL + '/addSongToHistory';
    private static PASSWORD_URL = UserService.USER_URL + '/password';
    private static PASSWORD_CHANGE_URL = UserService.PASSWORD_URL + '/requestChange';
    private static CHECK_HAS_IMAGE_URL = UserService.USER_URL + '/checkProfileImage';
    private static SET_IMAGE_URL = UserService.USER_URL + '/uploadProfileImage';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(callback: (user: User) => void): void {
        this.httpRequest.get(UserService.GET_USERNAME_URL)
            .subscribe((usernameResponse: ApiResponse) => {
                if (usernameResponse.success) {
                    this.httpRequest.get(
                        UserService.GET_USER_BY_USERNAME_URL + '/' + usernameResponse.responseData)
                        .subscribe((userResponse: ApiResponse) => {
                            const user: User = userResponse.responseData;
                            this.getPrivateSession()
                                .subscribe((privateSession: boolean) => {
                                    user.privateSession = privateSession;
                                    callback(user);
                                });
                        });
                } else {
                    callback(null);
                }
            });
    }

    public getUserByUsername(username: string): Observable<User> {
        return this.httpRequest.get(UserService.GET_USER_BY_USERNAME_URL + '/' + username)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public deleteUser(userId: number): Observable<boolean> {
        return this.httpRequest.delete(UserService.USER_URL, userId)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public sendChangePasswordEmail(): Observable<boolean> {
        return this.httpRequest.get(UserService.PASSWORD_CHANGE_URL)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public changePassword(securityCode: number, newPassword: string): Observable<boolean> {
        const body = {
            'securityCode': securityCode,
            'newPassword': newPassword
        };
        return this.httpRequest.put(UserService.PASSWORD_URL, body)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public getFollowedUsers(): Observable<User[]> {
        return this.httpRequest.get(UserService.FOLLOWED_USERS_URL)
            .map((response: ApiResponse) => {
                return response.responseData;
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
                return response.responseData;
            });
    }

    public getIsPremium(callback: (b: boolean) => any): void {
        const getUserCallback: (user: User) => void = (user: User) => {
            if (!user) {
                return;
            }
            for (let i = 0; i < user.roles.length; ++i) {
                if (user.roles[i].role === 'PREMIUM_USER') {
                    callback(true);
                    return;
                }
            }
            callback(false);
        };
        this.getUser(getUserCallback);
    }

    public getPrivateSession(): Observable<boolean> {
        return this.httpRequest.get(UserService.BROWSING_MODE_URL)
            .map((response: ApiResponse) => {
                if (response.success) {
                    return response.responseData;
                }
                return false;
            });
    }

    public setPrivateSession(isPrivateMode: boolean): Observable<boolean> {
        const url = (isPrivateMode) ? UserService.ENABLE_PRIVATE_MODE_URL : UserService.DISABLE_PRIVATE_MODE_URL;
        return this.httpRequest.get(url)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public getHistory(): Observable<Song[]> {
        return this.httpRequest.get(UserService.LISTENING_HISTORY_URL)
            .map((response: ApiResponse) => {
                if (response.success) {
                    return response.responseData;
                }
                return null;
            });
    }

    public getHistoryById(userId: number): Observable<Song[]> {
        return this.httpRequest.get(UserService.HISTORY_BY_ID_URL, userId)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public addSongIdToHistory(songId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.ADD_TO_HISTORY_URL, songId)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public getFollowedUsersHistory(): Observable<Map<number, Song>> {
        return this.httpRequest.get(UserService.FOLLOWED_USERS_HISTORY)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public setUserProfileImage(profileImage: any): Observable<boolean> {
        // return this.http.post(url, JSON.stringify(body), { headers: new HttpHeaders().set('Content-Type', 'application/json') })
        return this.httpRequest.post(UserService.SET_IMAGE_URL, profileImage)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }

    public getHasImageById(userId: number): Observable<boolean> {
        return this.httpRequest.get(UserService.CHECK_HAS_IMAGE_URL, userId)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    // public getUserPaymentInfo(userId: number): PaymentInfo {
    //     return this.httpRequest.get(UserService.PAYMENT_INFO_URL + "/" + userId)
    //     .subscribe((paymentInfo: PaymentInfo) => {
    //         return paymentInfo;
    //     });
    // }
}
