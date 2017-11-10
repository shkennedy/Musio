import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './landing-pages/login/login.component'
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumViewComponent } from './content-pages/album-view/album-view.component';
import { TopNavComponent } from './content-home/top-nav/top-nav.component';
import { LeftNavComponent } from './content-home/left-nav/left-nav.component';
import { RightNavComponent } from './content-home/right-nav/right-nav.component';
import { AudioPlayerComponent } from './content-home/audio-player/audio-player.component';
import { ContentHomeComponent } from './content-home/content-home.component';

const appRoutes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: ContentHomeComponent },
  { path: 'recover-password', component: RecoverPasswordComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'album', component: AlbumViewComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    RecoverPasswordComponent,
    AlbumViewComponent,
    TopNavComponent,
    LeftNavComponent,
    RightNavComponent,
    AudioPlayerComponent,
    ContentHomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
