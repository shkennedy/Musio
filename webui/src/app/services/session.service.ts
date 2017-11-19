import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../models/user.model';

export interface SessionInfo {
    token: string,
    userId: number
}

@Injectable()
export class SessionService {

    private static sessionInfoKey: string = "sessionInfo";
    private storage: Storage = sessionStorage;

    constructor() {}

    public storeSessionInfo(userId: number, token: string) {
        let sessionInfo: SessionInfo = {'userId': userId, 'token': token};
        this.storage.setItem(SessionService.sessionInfoKey, sessionInfo.toString());
    }

    public removeSessionInfo() {
        this.storage.removeItem(SessionService.sessionInfoKey);
    }

    public isLoggedIn(): boolean {
        return this.storage.getItem(SessionService.sessionInfoKey)? true : false;
    }

    public getSessionInfo(): SessionInfo {
        let sessionInfoString: string = this.storage.getItem(SessionService.sessionInfoKey);
        if (sessionInfoString) {
            let sessionInfo: SessionInfo = JSON.parse(this.storage.getItem(SessionService.sessionInfoKey));
            return sessionInfo;
        } else {
            throw Error('No current session'); 
        }
    }

    public getStoredToken(): string {
        let sessionInfo: SessionInfo = this.getSessionInfo();
        return sessionInfo.token;
    }

    public getUserId(): number {
        let sessionInfo: SessionInfo = this.getSessionInfo();
        return sessionInfo.userId;
    }
}
