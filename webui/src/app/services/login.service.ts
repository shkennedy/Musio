import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

export interface LoginRequestParam {
    username: string;
    password: string;
}

export interface RegisterRequestParam {
    username: string;
    password: string;
    email: string;
}

@Injectable()
export class LoginService {

    private static LOGIN_URL = '/login';
    private static LOGOUT_URL = '/logout';
    private static REGISTER_URL = '/register';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public tryLogin(username: string, password: string): Observable<boolean> {
        const body: LoginRequestParam = {
            'username': username,
            'password': password,
        };
        return this.httpRequest.post(LoginService.LOGIN_URL, body)
            .map((response: ApiResponse) => {
                if (response.success) {
                    // this.sessionService.storeSessionInfo(response.data, 'tokenStr'); // TODO token string
                }
                return response.success;
            });
    }

    public logout(navigatetoLogout: boolean = true): Observable<boolean> {
        return this.httpRequest.get(LoginService.LOGOUT_URL)
            .map((response: ApiResponse) => {
                if (response.success) {
                    // this.sessionService.removeSessionInfo();
                }
                return response.success;
            });
    }

    public register(username: string, password: string, email: string): Observable<boolean> {
        const body: RegisterRequestParam = {
            'username': username,
            'password': password,
            'email': email
        };
        return this.httpRequest.post(LoginService.REGISTER_URL, body)
            .map((response: ApiResponse) => {
                return response.success;
            });
    }
}
