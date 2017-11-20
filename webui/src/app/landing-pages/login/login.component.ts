import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    //previous function
    var screenheight = window.innerHeight;
    var html = document.getElementsByTagName('html')[0];
    html.setAttribute("style", "--global-screen-height: " + (screenheight) + "px");
  }

  onLogIn() {
    console.log("No DB exists. Log in accepted");
    this.router.navigate(['/']);
  }

}
