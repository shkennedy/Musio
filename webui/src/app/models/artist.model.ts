import { Album } from './album.model'
import { Genre } from './genre.model'
import { RelatedArtist } from './related_artist.model'

export class Artist{
  public artist_id: number;
  public artist_name: string;
  public bio: string;
  public albums: Album[];
  public genres: Genre[];
  public artist_banner: File;
  //additional pictures for the bio section
  public artist_pictures: File[];
  public related_artists: RelatedArtist[];
  public location: string;
  public followers: number;
}
