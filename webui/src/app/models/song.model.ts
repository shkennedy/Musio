import { Album } from './album.model';
import { Artist } from './artist.model';
import { Genre } from './genre.model';
import { Playlist } from './playlist.model';

export class Song {
    public id: number;
    public title: string;
    public duration: number;
    public lyrics: string;
    public mbid: string;
    public artist: Artist;

    public album: Album;
    public durationString: string;
    public trackNumber: number;
    public audioFileUrl: string;
    public isFavorited: boolean;
    public playlistsMap: Map<number, Playlist>;
    public playlistsList: Playlist[];
}
