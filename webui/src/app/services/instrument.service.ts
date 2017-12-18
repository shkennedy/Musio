import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

import { HttpRequestService, ApiResponse } from './httpRequest.service';
import { FileService } from './file.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';
import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';
import { Instrument } from '../models/instrument.model';

export interface BrowseInstrumentResponse {
    songs: Song[];
    artists: Artist[];
    albums: Album[];
}

@Injectable()
export class InstrumentService {

    private static INSTRUMENT_URL = '/instrument';
    private static BROWSE_URL = '/browse';

    constructor(
        private router: Router,
        private fileService: FileService,
        private httpRequest: HttpRequestService
    ) { }

    public getBrowseByInstrumentId(instrumentId: number): Observable<BrowseInstrumentResponse> {
        return this.httpRequest.get(InstrumentService.INSTRUMENT_URL + InstrumentService.BROWSE_URL, instrumentId)
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }

    public getAllInstruments(): Observable<Instrument[]> {
        return this.httpRequest.get(InstrumentService.INSTRUMENT_URL + '/getAll')
            .map((response: ApiResponse) => {
                return response.responseData;
            });
    }
}
