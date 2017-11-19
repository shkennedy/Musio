import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { SessionService } from './session.service';

export interface LoginRequestParam {
    username: string;
    password: string;
}

export interface RegisterRequestParam {
    username: string;
    password: string;
    email:    string    
}

@Injectable()
export class LoginService {

    private static LOGIN_URL: string    = '/login';
    private static LOGOUT_URL: string   = '/logout';
    private static REGISTER_URL: string = '/register';

    constructor(
        private router: Router,
        private sessionService: SessionService,
        private httpRequest: HttpRequestService
    ) { }

    public tryLogin(username: string, password: string): boolean {
        let body: LoginRequestParam = {
            'username': username,
            'password': password,
        }
        return this.httpRequest.post(LoginService.LOGIN_URL, body)
        .subscribe((response: ApiResponse) => {
            if (response.success) {
                this.sessionService.storeSessionInfo(response.data, 'tokenStr'); // TODO token string
            }
            return response.success;
        });
    }

    public logout(navigatetoLogout: boolean = true): boolean {
        return this.httpRequest.get(LoginService.LOGOUT_URL)
        .subscribe((response: ApiResponse) => {
            if (response.success) { 
                this.sessionService.removeSessionInfo();
            }
            return response.success;
        });
    }

    public register(username: string, password: string, email: string): boolean {
        let body: RegisterRequestParam = {
            'username': username,
            'password': password,
            'email':    email
        }
        return this.httpRequest.post(LoginService.REGISTER_URL, body)
        .subscribe((response: ApiResponse) => {
            return response.success;
        });
    }
}
