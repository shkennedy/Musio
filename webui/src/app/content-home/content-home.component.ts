import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AudioPlayerComponent } from './audio-player/audio-player.component'
import { TopNavComponent } from './top-nav/top-nav.component'
import { LeftNavComponent } from './left-nav/left-nav.component'
import { RightNavComponent } from './right-nav/right-nav.component'

@Component({
  selector: 'app-content-home',
  templateUrl: './content-home.component.html',
  styleUrls: ['./content-home.component.css']
})
export class ContentHomeComponent implements OnInit {

  constructor(
    private router: Router) { }

  ngOnInit() {

  }

}
