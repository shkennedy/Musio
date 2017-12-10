import { Album } from './album.model';
import { Artist } from './artist.model';
import { Genre } from './genre.model';

export class Song {
    public id: number;
    public title: string;
    public duration: number;
    public lyrics: string;
    public mbid: string;
    public artist: Artist;

    public album: Album;
    public trackNumber: number;
    public audioFileUrl: string;
}
