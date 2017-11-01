import { Component, OnInit } from '@angular/core';
import {Http, Response} from '@angular/http'
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {

  username:string;
  

  constructor(private http: Http) {
  
  }
  
  ngOnInit() {

    this.http.get('/user/getUsername')
      .subscribe(
        resp => {
          this.username = resp.text();
        },
        err => {
          console.log('Something went wrong!');
        }
      );
  }

}
