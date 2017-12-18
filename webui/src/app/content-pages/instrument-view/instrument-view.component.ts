import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatRow, MatSort } from '@angular/material';

import { InstrumentService, BrowseInstrumentResponse } from '../../services/instrument.service';
import { FileService } from '../../services/file.service';
import { AudioPlayerProxyService } from '../../services/audioPlayerProxy.service';
import { FavoritesService } from '../../services/favorites.service';
import { PlaylistService } from '../../services/playlist.service';

import { SongTableManager } from '../../shared/songTableManager.module';

import { Album } from '../../models/album.model';
import { Artist } from '../../models/artist.model';
import { Instrument } from '../../models/instrument.model';
import { Song } from '../../models/song.model';

@Component({
    selector: 'app-instrument-view',
    templateUrl: './instrument-view.component.html',
    styleUrls: ['./instrument-view.component.css']
})
export class InstrumentViewComponent implements OnInit {

    private isLoaded = false;
    private artists: Artist[];
    private albums: Album[];

    @ViewChild(MatSort) sort: MatSort;
    private songTableManager: SongTableManager;

    constructor(
        private router: Router,
        private audioPlayerProxyService: AudioPlayerProxyService,
        private instrumentService: InstrumentService,
        private favoritesService: FavoritesService,
        private fileService: FileService,
        private playlistService: PlaylistService
    ) { }

    ngOnInit() {
        const url: string[] = this.router.url.split('/');
        this.instrumentService.getBrowseByInstrumentId(Number(url[url.length - 1]))
            .subscribe((browseResults: BrowseInstrumentResponse) => {
                this.albums = browseResults.albums;
                if (this.albums) {
                    this.albums.forEach((album: Album) => {
                        album.albumArtUrl =
                            this.fileService.getAlbumImageURLByIdAndSize(album.id, true);
                    });
                }

                this.artists = browseResults.artists;
                if (this.artists) {
                    this.artists.forEach((artist: Artist) => {
                        artist.artistImageUrl =
                            this.fileService.getArtistImageURLByIdAndSize(artist.id, true);
                    });
                }

                this.songTableManager =
                    new SongTableManager(this.audioPlayerProxyService, this.favoritesService, this.playlistService);
                this.songTableManager.setSongs(browseResults.songs);
                setTimeout(() => {
                    this.songTableManager.setSort(this.sort);
                }, 2000);

                this.isLoaded = true;
            });
    }

}
