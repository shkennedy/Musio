import { User } from './user.model';
import { Song } from './song.model';
import { File } from './file.model';

export class Playlist {
    public id: number;
    public isCollaborative: boolean;
    public isPrivate: boolean;
    public name: string;
    public ownerId: number;
    public songs: Song[];
    public playlistImageId: number;

    public playlistImageUrl: string;
}
