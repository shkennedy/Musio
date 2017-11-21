import { User } from './user.model'
import { Song } from './song.model'

export class Playlist {
  public id: number;
  public isCollaborative: boolean;
  public isPrivate: boolean;
  public name: string;
  public owner: User;
  public songs: Song[];
}
