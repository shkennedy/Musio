import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { HttpModule } from '@angular/http'
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule} from './app-routing.module'

//import { AuthGuard } from './auth-guard.service'

import { AppComponent } from './app.component';
import { LoginComponent } from './landing-pages/login/login.component';
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumViewComponent } from './content-pages/album-view/album-view.component';
import { ArtistViewComponent } from './content-pages/artist-view/artist-view.component';
import { TopNavComponent } from './content-home/top-nav/top-nav.component';
import { LeftNavComponent } from './content-home/left-nav/left-nav.component';
import { RightNavComponent } from './content-home/right-nav/right-nav.component';
import { AudioPlayerComponent } from './content-home/audio-player/audio-player.component';
import { ContentHomeComponent } from './content-home/content-home.component';
import { PlaylistViewComponent } from './content-pages/playlist-view/playlist-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { SongItemComponent } from './content-pages/song-view/song-item/song-item.component';
import { AlbumItemComponent } from './content-pages/album-view/album-item/album-item.component';

import { AlbumService } from './services/album.service';
import { ArtistService } from './services/artist.service';
import { FavoritesService } from './services/favorites.service';
import { GenreService } from './services/genre.service';
import { HttpRequestService } from './services/httpRequest.service';
import { SessionService } from './services/session.service';
import { SongService } from './services/song.service';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    PageNotFoundComponent,
    RecoverPasswordComponent,
    AlbumViewComponent,
    ArtistViewComponent,
    TopNavComponent,
    LeftNavComponent,
    RightNavComponent,
    AudioPlayerComponent,
    ContentHomeComponent,
    PlaylistViewComponent,
    SongViewComponent,
    BrowseComponent,
    SongItemComponent,
    AlbumItemComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    HttpClientModule
  ],
  //services go here
  providers: [
    AlbumService,
    ArtistService,
    FavoritesService,
    GenreService,
    HttpRequestService,
    SessionService,
    SongService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
