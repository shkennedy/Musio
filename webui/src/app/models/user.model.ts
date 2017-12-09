import { Role } from './role.model';
import { Song } from './song.model';
import { Artist } from './artist.model';
import { Album } from './album.model';
import { Station } from './station.model';
import { GeoLocation } from './geoLocation.model';
import { Genre } from './genre.model';
import { Playlist } from './playlist.model';

export class User {
    id: number;
    email: string;
    username: string;
    roles: Role[];
    favoriteSongs: Song[];
    favoritePlaylists: Playlist[];
    favoriteArtists: Artist[];
    favoriteStations: Station[];
    favoriteGenres: Genre[];
    followedUsers: User[];
    geoLocation: GeoLocation;
    profileImageUrl: string;
}
