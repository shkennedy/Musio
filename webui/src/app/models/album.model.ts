import { Song } from './song.model';
import { Genre } from './genre.model';
import { Artist } from './artist.model';
import { Image } from './image.model';

export class Album {
  public id: number;
  public title: string;
  public artists: Artist[];
  public songs: Song[];
  private mbid: string;
  public releaseDate: Date;
  public albumArt: Image;

  public artistId: number;
  public artistName: string;
  public albumArtId: number;
  public albumArtUrl: string;
  public isFavorited: boolean;
  public fullFileId: number;
}
