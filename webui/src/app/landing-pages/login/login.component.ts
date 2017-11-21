import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SongService } from '../../services/song.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,
    private songService: SongService
  ) { }

  ngOnInit() {
    //previous function
    var screenheight = window.innerHeight;
    var html = document.getElementsByTagName('html')[0];
    html.setAttribute("style", "--global-screen-height: " + (screenheight) + "px");
  }

  onLogIn(form:NgForm) {
    const value=form.value;
    this.songService.getSongById(2)
    .subscribe((song: any) => {
      console.log('worked');
    });
  }

}
