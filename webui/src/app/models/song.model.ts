import { Genre } from './genre.model'
export class Song{
  public song_id: number;
  public song_name: string;
  public audio: File;
  public lyrics: string;
  public duration: string;
  public genre: Genre[]
  public artist_id: number;
  public artist_name: string;
  public album_id: number;
  public album_name: string;
}
