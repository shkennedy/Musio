import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

import { Playlist } from '../../models/playlist.model'
@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {
  public playlists:Playlist[];
  // pass the playlists in this constructor
  constructor(private router: Router) {
    // sample data
    let p=new Playlist();
    p.playlist_name="playlist1";
    p.playlist_id=1;
    let p2=new Playlist();
    p2.playlist_name="playlist2";
    p2.playlist_id=2;
    let p3=new Playlist();
    p3.playlist_name="PLAYYYYYYYYYYYYYLIST3";
    p3.playlist_id=3;
    // let p4=new Playlist();
    // p4.playlist_name="playlist4";
    // p4.playlist_id=4;
    // let p5=new Playlist();
    // p5.playlist_name="playlist5";
    // p5.playlist_id=5;
    // let p6=new Playlist();
    // p6.playlist_name="playlist6";
    // p6.playlist_id=6;

    this.playlists=[p,p2,p3];
  }
  ngOnInit() {
  }

  navigateBrowse(){this.router.navigate(["/"]);}

  navigateRadio(){this.router.navigate(["/radio"]);}

  navigateSongs(){this.router.navigate(["/songs"]);}

  navigaterArtists(){this.router.navigate(["/artists"]);}

  navigateAlbums(){this.router.navigate(["/albums"]);}

  navigatePlaylist(playlist_id:number){this.router.navigate(["/playlist",String(playlist_id)]);}

}
