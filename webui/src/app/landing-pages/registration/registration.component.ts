import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    //previous function
    var screenheight = window.innerHeight;
    var html = document.getElementsByTagName('html')[0];
    html.setAttribute("style", "--global-screen-height: " + (screenheight) + "px");
  }

}
