import { Album } from './album.model';
import { Genre } from './genre.model';
import { GeoLocation } from './geoLocation.model';
import { RelatedArtist } from './related_artist.model';
import { User } from './user.model'

export class Artist {
  public id: number;
  public bio: string;
  public mbid: string;
  public name: string;
  public sortName: string;
  public website: string;
  public musicLabel: User;
  public geoLocation: GeoLocation;
  public artistImage: File;
  public backgroundArt: File;
}
