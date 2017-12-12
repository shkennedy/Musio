import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { PlaylistService } from '../../services/playlist.service';
import { FavoritesService } from '../../services/favorites.service';

import { Playlist } from '../../models/playlist.model';
import { SongService } from '../../services/song.service';

@Component({
    selector: 'app-left-nav',
    templateUrl: './left-nav.component.html',
    styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

    public playlists: Playlist[];
    public errMsg: String;

    // pass the playlists in this constructor
    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
        private songService: SongService
    ) {
        let p=new Playlist();
        p.id=5;
        p.name="PlayList"
        this.playlists=[p,p,p,p,p,p,p,p,p];
        // sample data
        // let p=new Playlist();
        // p.name="playlist1";
        // p.id=1;
        // this.playlists=[p];
    }

    ngOnInit() {
        this.favoritesService.getFavoritePlaylists()
        .subscribe(
            (playlists: Playlist[]) => {
                this.playlists = playlists;
            },
            (error: any) => {
                this.errMsg = error;
            }
        )
    }

    navigatePlaylist(playlistId: number) {
        this.router.navigate(['/playlist', playlistId]);
    }
}
