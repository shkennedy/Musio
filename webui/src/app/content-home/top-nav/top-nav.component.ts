import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model'
@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  public button: boolean=true;
  constructor() {

   }

  ngOnInit() {
  }

  goPremium(){

  }

}
