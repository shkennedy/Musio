// import { AppConfig } from '../../app-config';
import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

export interface ApiResponse {
    success: boolean;
    message: string;
    responseData: any;
}

@Injectable()
export class HttpRequestService {

    constructor(
        // private appConfig: AppConfig,
        private http: HttpClient,
        private router: Router,
    ) { }

    public get(route: string, id: number = -1): Observable<ApiResponse> {
        let url = route;
        if (id !== -1) {
            url += '/' + id.toString();
        }
        return this.http.get(url)
            .catch(function (error: any) {
                return Observable.throw(error || 'Server error');
            });
    }

    public post(route: string, body: Object, id: number = -1): Observable<ApiResponse> {
        let url = route;
        if (id !== -1) {
            url += '/' + id.toString();
        }
        return this.http.post(url, JSON.stringify(body), { headers: this.getHeaders() })
            .catch(function (error: any) {
                console.log('error: ' + error);
                return Observable.throw(error || 'Server error');
            });
    }

    public put(route: string, body: Object, id: number = -1): Observable<ApiResponse> {
        let url = route;
        if (id !== -1) {
            url += '/' + id.toString();
        }
        return this.http.put(url, JSON.stringify(body), { headers: this.getHeaders() })
            .catch(function (error: any) {
                console.log('error: ' + error);
                return Observable.throw(error || 'Server error');
            });
    }

    public delete(route: string, id: number = -1): Observable<ApiResponse> {
        let url = route;
        if (id !== -1) {
            url += '/' + id.toString();
        }
        return this.http.delete(url, { headers: this.getHeaders() })
            .catch(function (error: any) {
                console.log('error: ' + error);
                return Observable.throw(error || 'Server error');
            });
    }

    private getHeaders(): HttpHeaders {
        let headers = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json');
        return headers;
    }
}
