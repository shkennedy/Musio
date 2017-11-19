// import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
// import { Observable } from 'rxjs/Observable'
// import { Injectable } from '@angular/core'
// //whatever service to be imported
// import { AuthService } from './auth.service'
//
// export class AuthGuard implements CanActivate
// {
//   constructor(private authService: AuthService, private router: Router){}
//
//   canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): (Observable<boolean> | Promise<boolean> | boolean){
//     //returns the promise
//     //interally return true if canActivate else redirect
//     //authenticated is an attyribute of the service class
//     return this.authService.isLoggedIn()
//       .then(
//         (authenticated: boolean)=>{
//           if(authenticated)return true;
//           else this.router.navigate(['/login'])
//         }
//     );
//   }
// }
