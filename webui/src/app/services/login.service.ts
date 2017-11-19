import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService } from './httpRequest.service';

export interface LoginRequestParam {
    username: string;
    password: string;
}

@Injectable()
export class LoginService {

    private static LOGIN_URL: string = '/login';
    private static LOGOUT_URL: string = '/logout';
    private static REGISTER_URL: string = '/register';

    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private httpRequest: HttpRequestService
    ) { }

    private tryLogin(username: string, password: string): boolean {
        let body: LoginRequestParam = {
            "username": username,
            "password": password,
        }
        this.httpRequest.post(LoginService.LOGIN_URL, body);
    }

    getToken(username: string, password: string): Observable<any> {

        let bodyData: LoginRequestParam = {
            "username": username,
            "password": password,
        }
        let loginDataSubject: Subject<any> = new Subject<any>(); // Will use this subject to emit data that we want after ajax login attempt
        let loginInfoReturn: LoginInfoInStorage; // Object that we want to send back to Login Page

        this.httpRequest.post('login', bodyData)
            .subscribe(jsonResp => {
                if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationStatus === "SUCCESS") {
                    //Create a success object that we want to send back to login page
                    loginInfoReturn = {
                        "success": true,
                        "message": jsonResp.operationMessage,
                        "landingPage": this.landingPage,
                        "user": {
                            "userId": jsonResp.item.userId,
                            "email": jsonResp.item.emailAddress,
                            "displayName": jsonResp.item.firstName + " " + jsonResp.item.lastName,
                            "token": jsonResp.item.token,
                        }
                    };

                    // store username and jwt token in session storage to keep user logged in between page refreshes
                    this.userInfoService.storeUserInfo(JSON.stringify(loginInfoReturn.user));
                }
                else {
                    //Create a faliure object that we want to send back to login page
                    loginInfoReturn = {
                        "success": false,
                        "message": jsonResp.msgDesc,
                        "landingPage": "/login"
                    };
                }
                loginDataSubject.next(loginInfoReturn);
            });

        return loginDataSubject;
    }

    logout(navigatetoLogout = true): void {
        // clear token remove user from local storage to log user out
        this.apiRequest

        this.userInfoService.removeUserInfo();
        if (navigatetoLogout) {
            this.router.navigate(["logout"]);
        }
    }
}
