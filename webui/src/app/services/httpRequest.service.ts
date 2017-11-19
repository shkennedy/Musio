// import { AppConfig } from '../../app-config';
import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { SessionService } from './session.service';

export interface ApiResponse {
    success: boolean,
    message: string,
    data: any
}

@Injectable()
export class HttpRequestService {

    private static BASE_URL: string = 'localhost:4200';

    constructor(
        // private appConfig: AppConfig,
        private http: HttpClient,
        private router: Router,
        private sessionService: SessionService
    ) { }

    public get(url: string, urlParams: {} = null): Observable<ApiResponse> {
        // Construct http params from map
        let httpParams: HttpParams = new HttpParams();
        Object.keys(urlParams).forEach((key: any) => {
            httpParams.append(key, urlParams[key]);
        });

        return this.http.get(HttpRequestService.BASE_URL + url, { headers: this.getHeaders(), params: urlParams })
            .catch(function (error: any) {
                if (error.status === 401 || error.status === 403) {
                    this.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    public post(url: string, body: Object): Observable<ApiResponse> {
        return this.http.post(HttpRequestService.BASE_URL + url, JSON.stringify(body), { headers: this.getHeaders() })
            .catch(function (error: any) {
                if (error.status === 401) {
                    this.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    public put(url: string, body: Object): Observable<ApiResponse> {
        return this.http.put(HttpRequestService.BASE_URL + url, JSON.stringify(body), { headers: this.getHeaders() })
            .catch(function (error: any) {
                if (error.status === 401) {
                    this.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    public delete(url: string): Observable<ApiResponse> {
        return this.http.delete(HttpRequestService.BASE_URL + url, { headers: this.getHeaders() })
            .catch(function (error: any) {
                if (error.status === 401) {
                    this.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    private getHeaders(): HttpHeaders {
        let headers = new HttpHeaders();
        let token = this.sessionService.getStoredToken();
        headers = headers.append('Content-Type', 'application/json');
        if (token !== null) {
            headers = headers.append("Authorization", token);
        }
        return headers;
    }
}
