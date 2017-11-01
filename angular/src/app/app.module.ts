import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import * as $ from 'jquery';
import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { UserMainComponent } from './components/user-main/user-main.component';
import { AudioPlayerComponent } from './components/user-main/audio-player/audio-player.component';
import { ContainerComponent } from './components/user-main/container/container.component';
import { ContentContainerComponent } from './components/user-main/content-container/content-container.component';
import { LeftNavComponent } from './components/user-main/left-nav/left-nav.component';
import { RightNavComponent } from './components/user-main/right-nav/right-nav.component';
import { TopMenuComponent } from './components/user-main/top-menu/top-menu.component';
import { LoginComponent } from './components/login/login.component';
import { AlbumComponent } from './components/user-main/album/album.component';
import { DummyContentComponent } from './components/user-main/dummy-content/dummy-content.component';
import { UpgradePageComponent } from './components/user-main/upgrade-page/upgrade-page.component';



@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserMainComponent,
    AudioPlayerComponent,
    ContainerComponent,
    ContentContainerComponent,
    LeftNavComponent,
    RightNavComponent,
    TopMenuComponent,
    LoginComponent,
    AlbumComponent,
    DummyContentComponent,
    UpgradePageComponent,
 
    // LoginComponent,


  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
