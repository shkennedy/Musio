import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { PlaylistService } from '../../services/playlist.service';
import { FavoritesService } from '../../services/favorites.service';
import { LeftBarProxyService } from '../../services/leftBarProxy.service';

import { Playlist } from '../../models/playlist.model';
import { SongService } from '../../services/song.service';

@Component({
    selector: 'app-left-nav',
    templateUrl: './left-nav.component.html',
    styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

    public playlists: Playlist[];
    public errMsg: string;
    public focusedTab: string;

    constructor(
        private router: Router,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService,
        private songService: SongService,
        private leftBarProxyService: LeftBarProxyService
    ) { }

    ngOnInit() {
        this.refreshPlaylists();
        this.leftBarProxyService.registerListeners(this.relinquishFocus, this.refreshPlaylists);
    }

    private relinquishFocus = (): void => {
        this.focusedTab = '';
    }

    private refreshPlaylists = (): void => {
        this.favoritesService.getFavoritePlaylists()
        .subscribe(
            (playlists: Playlist[]) => {
                this.playlists = playlists;
            },
            (error: any) => {
                this.errMsg = error;
            });
    }

    private setFocsedTab(tabName: string): void {
        this.focusedTab = tabName;
    }

    private getFocusClass(tabName: string): void {
        //
    }

    gotoPlaylist(playlistId: number) {
        this.router.navigate(['/playlist', playlistId]);
    }

    private getClass(path: string): string {
        console.log('test');
        if (this.router.url === path) {
            return 'selected;';
        } else {
            return '';
        }
    }
}
