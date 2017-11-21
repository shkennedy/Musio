import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './landing-pages/login/login.component'
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumsViewComponent } from './content-pages/album-views/album-collection-view/albums-view.component';
import { AlbumDetailComponent } from './content-pages/album-views/single-album-view/album-detail.component';
import { ArtistsViewComponent } from './content-pages/artist-views/artist-collection-view/artists-view.component';
import { ArtistDetailComponent } from './content-pages/artist-views/single-artist-view/artist-detail.component';
import { PlaylistsViewComponent } from './content-pages/playlist-views/playlist-collection-view/playlists-view.component';
import { PlaylistViewComponent } from './content-pages/playlist-views/single-playlist-view/playlist-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { ContentHomeComponent } from './content-home/content-home.component';
import { RadioViewComponent } from './content-pages/radio-view/radio-view.component';
import { GoPremiumComponent } from './content-pages/go-premium/go-premium.component';

const appRoutes: Routes = [
  { path: '', component: ContentHomeComponent, children:
    [
      { path: '', component: BrowseComponent },
      { path: 'albums', component: AlbumsViewComponent, children:
        [
          { path: ':id', component: AlbumDetailComponent }
        ]
      },
      { path: 'artists', component: ArtistsViewComponent, children:
        [
          { path: ':id', component: ArtistDetailComponent }
        ]
      },
      { path: 'playlists', component: PlaylistsViewComponent, children:
        [
          { path: ':id',  component: PlaylistViewComponent}
        ]
      },
      { path: 'songs', component: SongViewComponent },
      { path: 'radio', component: RadioViewComponent },
      { path: 'go-premium', component: GoPremiumComponent}
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
