import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: {username: string, 
          password: string};
  errMsg: string = '';

  constructor(
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit() {}

  tryLogin(): void {
    let username: string = this.model.username;
    let password: string = this.model.password;

    // if (!username || !password) {
    //   this.errMsg = 'Please enter username and password.';
    // }

    // let success: boolean = this.loginService.tryLogin(username, password);
    // if (success) {
    //   this.router.navigate(['']);
    // } else {
    //   this.errMsg = 'Invalid username or password.';
    // }
  }

}
