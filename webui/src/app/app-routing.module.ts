import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './landing-pages/login/login.component'
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumsViewComponent } from './content-pages/album-views/album-collection-view/albums-view.component';
import { ArtistsViewComponent } from './content-pages/artist-views/artist-collection-view/artists-view.component';
import { PlaylistsViewComponent } from './content-pages/playlist-views/playlist-collection-view/playlists-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { ContentHomeComponent } from './content-home/content-home.component';

const appRoutes: Routes = [
  { path: '', component: ContentHomeComponent, children:
    [
      { path: '', component: BrowseComponent },
      { path: 'albums', component: AlbumsViewComponent, children:
        [
          { path: ':id', component: AlbumsViewComponent }
        ]
      },
      { path: 'artists', component: ArtistsViewComponent, children:
        [
          { path: ':id', component: ArtistsViewComponent }
        ]
      },
      { path: 'playlists', component: PlaylistsViewComponent, children:
        [
          { path: ':id',  component: PlaylistsViewComponent}
        ]
      },
      { path: 'songs', component: SongViewComponent}
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
