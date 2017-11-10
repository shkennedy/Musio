import { User } from './user.model'
import { Song } from './song.model'
export class Playlist{
  public playlist_id: number;
  public playlist_name: string;
  public owner: User;
  public isPrivate: boolean;
  public isCollaborative: boolean;
  public songs: Song[];
  public date_created: Date;
}
