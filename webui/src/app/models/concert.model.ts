import { Venue } from './venue.model';

export class Concert {
    public id: number;
    public name: string;
    public venue: Venue;
    public date: Date;
}
