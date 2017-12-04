import { Artist } from './artist.model';
import { Genre } from './genre.model';

export class Song {
    public id: number;
    public title: string;
    public duration: string;
    public lyrics: string;
    public mbid: string;
    public artist: Artist;

    public audio: File;
}
