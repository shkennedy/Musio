import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    //previous function
    var screenheight = window.innerHeight;
    var html = document.getElementsByTagName('html')[0];
    html.setAttribute("style", "--global-screen-height: " + (screenheight) + "px");
  }

}
