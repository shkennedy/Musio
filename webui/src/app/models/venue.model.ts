import { Concert } from './concert.model';
import { GeoLocation } from './geoLocation.model';

export class Venue {
    public id: number;
    public addressId: number;
    public name: string;
    public concerts: Concert[];
    public geoLocation: GeoLocation;
}
