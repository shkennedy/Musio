import { GeoLocation } from './geoLocation.model';

export class Performer {
    public id: number;
    public firstName: string;
    public lastName: string;
    public mbid: number;
    public performerArtId: number;
    public geoLocation: GeoLocation;

    public performerArtUrl: string;
}
