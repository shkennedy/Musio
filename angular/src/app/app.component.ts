import { Component } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BLAH';

  ngOnInit(){
    // collapse left bar menu items jquery
        $(".Collapsable").click(function () {
        $(this).parent().children().toggle(90);
        $(this).toggle(1);
    });
  }
}
