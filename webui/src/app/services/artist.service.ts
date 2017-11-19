import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService } from './httpRequest.service';

import { Album } from '../models/album.model';
import { Artist } from '../models/artist.model';

@Injectable()
export class ArtistService {

    private static ARTIST_URL: string = '/artist';

    constructor(
        private router: Router,
        private httpRequest: HttpRequestService
    ) { }

    public getArtistById(artistId: number): Artist {
        return this.httpRequest.get(ArtistService.ARTIST_URL, {"artistId": artistId})
        .subscribe((artist: Artist) => {
            return artist;
        });
    }

    public addArtist(artist: Artist): boolean {
        return this.httpRequest.post(ArtistService.ARTIST_URL, artist)
        .subscribe((success: boolean) => {
            return success;
        });
    }

    public updateArtist(artist: Artist): boolean {
        return this.httpRequest.put(ArtistService.ARTIST_URL, artist)
        .subscribe((success: boolean) => {
            return success;
        });
    }

    public deleteArtist(artistId: number): boolean {
        return this.httpRequest.delete(ArtistService.ARTIST_URL + "/" + artistId)
        .subscribe((success: boolean) => {
            return success;
        });
    }
}
