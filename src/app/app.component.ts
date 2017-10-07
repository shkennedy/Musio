import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BLAH';

  ngOnInit(){
    
    $(document).ready(function () {
      $('label.tree-toggler').click(function () {
        $(this).parent().children('ul.tree').toggle(300);
      });
    });
  }
}
