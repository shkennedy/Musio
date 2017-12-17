import { MatTableDataSource, MatSort, MatTable } from '@angular/material';

import { AudioPlayerProxyService } from '../services/audioPlayerProxy.service';
import { FileService } from '../services/file.service';
import { FavoritesService } from '../services/favorites.service';
import { PlaylistService } from '../services/playlist.service';

import { Playlist } from '../models/playlist.model';
import { Song } from '../models/song.model';

export class SongTableManager {

    private songs: Map<number, Song>;
    private playlists: Map<number, Playlist>;
    private tableData: MatTableDataSource<Song>;
    private sort: MatSort;
    private buttonVisibilities: Map<number, boolean>;

    constructor(
        private audioPlayerProxyService: AudioPlayerProxyService,
        private favoritesService: FavoritesService,
        private playlistService: PlaylistService
    ) {
        this.songs = new Map();
        this.tableData = new MatTableDataSource();
        this.buttonVisibilities = new Map();

        this.playlists = new Map();
        this.favoritesService.getFavoritePlaylists()
            .subscribe((playlists: Playlist[]) => {
                playlists.forEach((playlist: Playlist) => {
                    this.playlists.set(playlist.id, playlist);
                });
            });
    }

    public setSort(sort: MatSort): void {
        this.sort = sort;
        this.tableData.sort = sort;
    }

    public getSongs(): Song[] {
        return this.tableData.data;
    }

    public setSongs(songs: Song[]): void {
        songs.forEach((song: Song) => {
            this.songs.set(song.id, song);
        });
        this.tableData.data = songs;
    }

    public addSong(song: Song): void {
        this.buttonVisibilities.set(song.id, false);
        this.songs.set(song.id, song);
        this.tableData.data.push(song);
        this.tableData = new MatTableDataSource(this.tableData.data);
        this.tableData.sort = this.sort;
    }

    public removeSong(song: Song): void {
        this.songs.delete(song.id);
        this.tableData.data.splice(this.tableData.data.indexOf(song), 1);
        this.tableData = new MatTableDataSource(this.tableData.data);
        this.tableData.sort = this.sort;
    }

    public hasPlaylists(): boolean {
        return this.playlists.size !== 0;
    }

    public getPlaylistsWhereIn(songId: number): Playlist[] {
        return this.songs.get(songId).playlistsList;
    }

    public getPlaylistsWhereNotIn(songId: number): Playlist[] {
        const song = this.songs.get(songId);
        const playlists: Playlist[] = [];
        const allPlaylistsIds = Array.from(this.playlists.keys());
        for (let i = 0; i < allPlaylistsIds.length; i += 1) {
            const playlistId = Number(allPlaylistsIds[i]);
            if (song.playlistsMap.has(playlistId)) {
                playlists.push(this.playlists.get(playlistId));
            }
        }
        return playlists;
    }

    // public removeSong(songId: number): void {
    //     this.songs.delete(songId);
    //     this.buttonVisibilities.delete(songId);
    //     this.tableData.data = songs;
    // }

    public showButtons(songId: number): void {
        this.buttonVisibilities.set(songId, true);
    }

    public hideButtons(songId: number): void {
        this.buttonVisibilities.set(songId, false);
    }

    public getButtonVisibility(songId: number): Object {
        return { 'visibility': this.buttonVisibilities.get(songId) ? 'visible' : 'hidden' };
    }

    public favoriteOrUnfavoriteSong(songId: number): void {
        if (this.songs.get(songId).isFavorited) {
            this.favoritesService.removeFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.songs.get(songId).isFavorited = !success;
                });
        } else {
            this.favoritesService.addFavoriteSongById(songId)
                .subscribe((success: boolean) => {
                    this.songs.get(songId).isFavorited = success;
                });
        }
    }

    public getFavoritedIcon(songId: number, isFavorited: boolean): Object {
        if (this.buttonVisibilities.get(songId)) {
            return (isFavorited) ? 'remove' : 'add';
        } else {
            return (isFavorited) ? 'favorite' : 'add';
        }
    }

    public playSong(songId: number): void {
        this.audioPlayerProxyService.playSong(songId);
    }

    public addSongToQueue(songId: number): void {
        this.audioPlayerProxyService.addSongToQueue(songId);
    }

    public addSongToPlaylist(playlistId: number, songId: number): void {
        this.playlistService.addSong(playlistId, songId)
            .subscribe((success: boolean) => {
                const song = this.songs.get(songId);
                const playlist = this.playlists.get(playlistId);
                song.playlistsMap.set(playlistId, playlist);
                song.playlistsList.push(playlist);
            });
    }

    public removeSongFromPlaylist(playlistId: number, songId: number): void {
        this.playlistService.removeSong(playlistId, songId)
            .subscribe((success: boolean) => {
                const song = this.songs.get(songId);
                const playlist = this.playlists.get(playlistId);
                song.playlistsMap.delete(playlistId);
                song.playlistsList.splice(song.playlistsList.indexOf(playlist), 1);
            });
    }
}
