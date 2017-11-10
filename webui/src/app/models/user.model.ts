export class User{
  public user_id: number;
  public user_name: string;
  public email: string;
  public user_banner: File;
  public following: User[];
  public followers: User[];
}
