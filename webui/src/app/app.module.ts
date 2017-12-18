import * as $ from 'jquery';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MatSliderModule,
         MatGridTile,
         MatGridList,
         MatIcon,
         MatTabsModule,
         MatCard,
         MatCardTitle,
         MatCardImage,
         MatCardActions,
         MatSortModule,
         MatMenu } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';

import { CapitalizePipe } from './pipes/capitalize-pipe.pipe';

import { AppComponent } from './app.component';
import { LoginComponent } from './landing-pages/login/login.component';
import { RegistrationComponent } from './landing-pages/registration/registration.component';
import { PageNotFoundComponent } from './landing-pages/page-not-found/page-not-found.component';
import { RecoverPasswordComponent } from './landing-pages/recover-password/recover-password.component';
import { AlbumsViewComponent } from './content-pages/album-views/album-collection-view/albums-view.component';
import { ArtistsViewComponent } from './content-pages/artist-views/artist-collection-view/artists-view.component';
import { AlbumDetailComponent } from './content-pages/album-views/single-album-view/album-detail.component';
import { ArtistDetailComponent } from './content-pages/artist-views/single-artist-view/artist-detail.component';
import { AlbumSongComponent } from './content-pages/album-views/single-album-view/album-song/album-song.component';
import { TopNavComponent } from './content-home/top-nav/top-nav.component';
import { LeftNavComponent } from './content-home/left-nav/left-nav.component';
import { AdBannerComponent } from './content-home/ad-banner/ad-banner.component';
import { AudioPlayerComponent } from './content-home/audio-player/audio-player.component';
import { ContentHomeComponent } from './content-home/content-home.component';
import { PlaylistsViewComponent } from './content-pages/playlist-views/playlist-collection-view/playlists-view.component';
import { PlaylistViewComponent } from './content-pages/playlist-views/single-playlist-view/playlist-view.component';
import { CreatePlaylistViewComponent } from './content-pages/playlist-views/create-playlist-view/create-playlist-view.component';
import { SongViewComponent } from './content-pages/song-view/song-view.component';
import { BrowseComponent } from './content-pages/browse/browse.component';
import { SongItemComponent } from './content-pages/song-view/song-item/song-item.component';
import { AlbumItemComponent } from './content-pages/album-views/album-collection-view/album-item/album-item.component';
import { ArtistItemComponent } from './content-pages/artist-views/artist-collection-view/artist-item/artist-item.component';
import { InstrumentViewComponent } from './content-pages/instrument-view/instrument-view.component';
import { GoPremiumComponent } from './content-pages/go-premium/go-premium.component';
import { SearchComponent } from './content-pages/search-view/search-view.component';
import { FollowedUsersBarComponent } from './content-home/followed-users-bar/followed-users-bar.component';
import { UserSettingsComponent } from './content-pages/user-views/user-settings-view/user-settings.component';
import { UsersComponent } from './content-pages/user-views/users-view/users.component';
import { PasswordDialogComponent } from './dialogs/password-dialog/password-dialog.component';
import { DeleteDialogComponent } from './dialogs/delete-dialog/delete-dialog/delete-dialog.component';
import { PictureDialogComponent } from './dialogs/picture-dialog/picture-dialog/picture-dialog.component';
import { PremiumDialogComponent } from './dialogs/premium-dialog/premium-dialog/premium-dialog.component';
import { FriendsDialogComponent } from './dialogs/friends-dialog/friends-dialog/friends-dialog.component';

import { AdService } from './services/ad.service';
import { AlbumService } from './services/album.service';
import { ArtistService } from './services/artist.service';
import { AudioPlayerProxyService } from './services/audioPlayerProxy.service';
import { FavoritesService } from './services/favorites.service';
import { FileService } from './services/file.service';
import { GenreService } from './services/genre.service';
import { HttpRequestService } from './services/httpRequest.service';
import { LoginService } from './services/login.service';
import { PlaylistService } from './services/playlist.service';
import { SearchService } from './services/search.service';
import { SongService } from './services/song.service';
import { UserService } from './services/user.service';
import { TopBarProxyService } from './services/topBarProxy.service';
import { FollowedUsersBarProxyService } from './services/followedUsersBarProxy.service';
import { InstrumentService } from './services/instrument.service';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegistrationComponent,
        PageNotFoundComponent,
        RecoverPasswordComponent,
        AlbumsViewComponent,
        ArtistsViewComponent,
        ArtistItemComponent,
        ArtistDetailComponent,
        TopNavComponent,
        LeftNavComponent,
        AdBannerComponent,
        AudioPlayerComponent,
        ContentHomeComponent,
        PlaylistsViewComponent,
        PlaylistViewComponent,
        CreatePlaylistViewComponent,
        SongViewComponent,
        BrowseComponent,
        SongItemComponent,
        AlbumItemComponent,
        AlbumDetailComponent,
        AlbumSongComponent,
        InstrumentViewComponent,
        GoPremiumComponent,
        SearchComponent,
        FollowedUsersBarComponent,
        UserSettingsComponent,
        PasswordDialogComponent,
        DeleteDialogComponent,
        PictureDialogComponent,
        CapitalizePipe,
        UsersComponent,
        PremiumDialogComponent,
        FriendsDialogComponent
    ],
    entryComponents: [PasswordDialogComponent,
                      DeleteDialogComponent,
                      PictureDialogComponent,
                      PremiumDialogComponent,
                      FriendsDialogComponent],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        HttpClientModule,
        SharedModule,
        CoreModule,
        ReactiveFormsModule
    ],
    providers: [
        AdService,
        AlbumService,
        ArtistService,
        AudioPlayerProxyService,
        FavoritesService,
        FileService,
        GenreService,
        HttpRequestService,
        LoginService,
        PlaylistService,
        SongService,
        SearchService,
        UserService,
        TopBarProxyService,
        FollowedUsersBarProxyService,
        InstrumentService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
