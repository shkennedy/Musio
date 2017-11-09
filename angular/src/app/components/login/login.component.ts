import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    function setColumnHeight2() {
      var screenheight = window.innerHeight;
      var html = document.getElementsByTagName('html')[0];
      console.log( document.getElementsByTagName('html')[0]);
      // html.setAttribute("style", "--screen-height: " + (screenheight - 108) + "px");
      html.setAttribute("style", "--global-screen-height: " + (screenheight) + "px");
    }


    setColumnHeight2();
  }

  onLogIn()
  {
    console.log("Logged in");
  }

}
