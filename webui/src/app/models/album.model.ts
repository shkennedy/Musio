import { Song } from './song.model';
import { Genre } from './genre.model';
import { Artist } from './artist.model';

export class Album {
  public id: number;
  public title: string;
  public artists: Artist[];
  public songs: Song[];
  public albumArtId: number;
  private mbid: string;
  public releaseDate: Date;

  public albumArtUrl: string;
}
