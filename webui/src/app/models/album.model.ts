import { Song } from './song.model'
import { Genre } from './genre.model'
export class Album{
  public album_id: number;
  public album_name: string;
  public album_art: File;
  public album_duration: number;
  public artist_id: number;
  public artist_name: string;
  public num_songs: number;
  public songs: Song[]
  public genre: Genre[];
  public release_date: Date;
}
