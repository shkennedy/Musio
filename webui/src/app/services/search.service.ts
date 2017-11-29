import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { HttpRequestService, ApiResponse } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Genre } from '../models/genre.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';
import { Station } from '../models/station.model';

export interface SearchResponse {
    albums: Album[];
    artists: Artist[];
    genres: Genre[];
    playlists: Playlist[];
    songs: Song[];
    stations: Station[];
}

@Injectable()
export class SearchService {

    private static SEARCH_URL: string = '/search';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    // public search(searchString: string): Observable<SearchResponse> { TODO
    //     return this.httpRequest.get(SearchService.SEARCH_URL, searchString)
    //     .map((response: ApiResponse) => {
    //         return response.responseData;
    //     });
    // }
}
