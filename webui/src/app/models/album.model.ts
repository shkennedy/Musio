import { Song } from './song.model';
import { Genre } from './genre.model';
import { Artist } from './artist.model';

export class Album {
  public id: number;
  public title: string;
  public artists: Artist[];
  public songs: Song[];
  public albumArtId: int;
  private mbid: string;
  public releaseDate: Date;
}
