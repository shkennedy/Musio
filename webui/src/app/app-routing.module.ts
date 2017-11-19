import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './landing-pages/login/login.component'
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumViewComponent } from './content-pages/album-view/album-view.component';
import { ArtistViewComponent } from './content-pages/artist-view/artist-view.component';
import { PlaylistViewComponent } from './content-pages/playlist-view/playlist-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { ContentHomeComponent } from './content-home/content-home.component';

const appRoutes: Routes = [
  { path: '', component: ContentHomeComponent, children:
    [
      { path: '', component: BrowseComponent },
      { path: 'albums', component: AlbumViewComponent, children:
        [
          { path: ':id', component: AlbumViewComponent }
        ]
      },
      { path: 'artists', component: ArtistViewComponent, children:
        [
          { path: ':id', component: ArtistViewComponent }
        ]
      },
      { path: 'playlists', component: PlaylistViewComponent, children:
        [
          { path: ':id',  component: PlaylistViewComponent}
        ]
      },
      { path: 'songs', component: SongViewComponent, children:
        [
          { path: ':id',  component: SongViewComponent}
        ]
      }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'recover-password', component: RecoverPasswordComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'not-found', component: PageNotFoundComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports:[ RouterModule ]
})
export class AppRoutingModule {

}