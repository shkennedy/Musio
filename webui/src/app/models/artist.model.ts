import { Album } from './album.model';
import { Genre } from './genre.model';
import { GeoLocation } from './geoLocation.model';
import { RelatedArtist } from './related_artist.model';
import { User } from './user.model';
import { Image } from './image.model';

export class Artist {
    public id: number;
    public bio: string;
    public mbid: string;
    public name: string;
    public sortName: string;
    public website: string;
    public musicLabel: User;
    public geoLocation: GeoLocation;
    public artistImage: Image;
    public thumbnailFileId: number;
    public backgroundArt: Image;

    public albums: Album[];
    public artistImageUrl: string;
    public backgroundArtUrl: string;
}
