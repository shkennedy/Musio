import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Genre } from '../models/genre.model';
import { Instrument } from '../models/instrument.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';
import { Station } from '../models/station.model';
import { User } from '../models/user.model';

export interface SearchResponse {
    albums: Album[];
    artists: Artist[];
    genres: Genre[];
    instruments: Instrument[];
    playlists: Playlist[];
    songs: Song[];
    users: User[];
    // stations: Station[]; TODO add when exists on back end
}

export interface BrowseResponse {
    songs: Song[];
    artists: Artist[];
    albums: Album[];
    playlists: Playlist[];
    instruments: Instrument[];
    genres: Genre[];
}

@Injectable()
export class SearchService {

    private static SEARCH_URL = '/search';
    private static BROWSE_URL = '/browse';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public search(searchString: string): Observable<SearchResponse> {
        return this.httpRequest.getWithUrlParams(SearchService.SEARCH_URL, searchString)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public getBrowse(): Observable<BrowseResponse> {
        return this.httpRequest.get(SearchService.BROWSE_URL)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }
}
