import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './landing-pages/login/login.component';
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumsViewComponent } from './content-pages/album-views/album-collection-view/albums-view.component';
import { AlbumDetailComponent } from './content-pages/album-views/single-album-view/album-detail.component';
import { ArtistsViewComponent } from './content-pages/artist-views/artist-collection-view/artists-view.component';
import { ArtistDetailComponent } from './content-pages/artist-views/single-artist-view/artist-detail.component';
import { PlaylistsViewComponent } from './content-pages/playlist-views/playlist-collection-view/playlists-view.component';
import { PlaylistViewComponent } from './content-pages/playlist-views/single-playlist-view/playlist-view.component';
import { CreatePlaylistViewComponent } from './content-pages/playlist-views/create-playlist-view/create-playlist-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { SearchComponent } from './content-pages/search-view/search-view.component';
import { ContentHomeComponent } from './content-home/content-home.component';
import { RadioViewComponent } from './content-pages/radio-view/radio-view.component';
import { GoPremiumComponent } from './content-pages/go-premium/go-premium.component';
import { UserSettingsComponent } from './content-pages/user-views/user-settings-view/user-settings.component';
// This component is for user pages
import { UsersComponent } from './content-pages/user-views/users-view/users.component';

const appRoutes: Routes = [
  { path: '', component: ContentHomeComponent, children:
    [
      { path: '', component: BrowseComponent },
      { path: 'albums', component: AlbumsViewComponent },
      { path: 'albums/:id', component: AlbumDetailComponent },
      { path: 'artists', component: ArtistsViewComponent },
      { path: 'artists/:id', component: ArtistDetailComponent },
      { path: 'playlists', component: PlaylistsViewComponent },
      { path: 'playlists/:id', component: PlaylistViewComponent },
      { path: 'createPlaylist', component: CreatePlaylistViewComponent },
      { path: 'songs', component: SongViewComponent },
      { path: 'radio', component: RadioViewComponent },
      { path: 'go-premium', component: GoPremiumComponent },
      { path: 'browse', component: BrowseComponent },
      { path: 'search/:searchQuery', component: SearchComponent },
      { path: 'userSettings', component: UserSettingsComponent },
      { path: 'user/:id', component: UsersComponent }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'recover-password', component: RecoverPasswordComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'not-found', component: PageNotFoundComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {

}
