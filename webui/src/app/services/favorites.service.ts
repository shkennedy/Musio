import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfoService, LoginInfoInStorage } from '../user-info.service';
import { HttpRequestService } from './httpRequest.service';

@Injectable()
export class FavoritesService {

    private static FAVORITES_URL: string 	= '/favorites';
	private static ALBUM_URL: string 		= FAVORITES_URL + '/albums';
	private static ARTIST_URL: string 		= FAVORITES_URL + '/artists';
	private static GENRE_URL: string	 	= FAVORITES_URL + '/genres';
	private static PLAYLIST_URL: string 	= FAVORITES_URL + '/playlists';
	private static SONG_URL: string 		= FAVORITES_URL + '/songs';
	private static STATION_URL: string	 	= FAVORITES_URL + '/stations';

    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private httpRequest: HttpRequestService
    ) { }

    public getUser(): User {
        userInfoService.
        httpRequest.get(USER_URL, {"userId": userId});
    }
    
    public getUserPaymentInfo(userId: number): {
        httpRequest.get(PAYMENT_INFO_URL, {"userId": userId});
    }

	public getUserPaymentInfo(userId: number): {
        httpRequest.get(PAYMENT_INFO_URL, {"userId": userId});
    }
}

@RequestMapping(value={"/getUsername"}, method = RequestMethod.GET)
	public @ResponseBody String getMyUsername(HttpSession session){
		User user = (User) session.getAttribute("user");
		return user.getUsername();
	}

	// TODO -- remove this
	// for testing (http://localhost:8080/user/get/test_admin) to see what it returns
	@GetMapping(path = "/get/{var}")
	public @ResponseBody User getAUser(@PathVariable String var)
	{
		return userService.findUserByUsername(var);
	}

	@GetMapping(path="/whoami")
	public @ResponseBody User whoAmI(HttpSession session) {
		return (User) session.getAttribute("user");
	}

	/**
	 * Re-query the user stored in the session from the database.
	 */
	@RequestMapping(value={"/refreshCurrentUser"}, method = RequestMethod.GET)
	public @ResponseBody boolean refreshUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", userService.findUserByUsername(user.getUsername()));
		return true;
	}

	@RequestMapping(value={"/favorites/albums"}, method = RequestMethod.GET)
	public @ResponseBody Set<Album> getFavoriteAlbums(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteAlbums();
	}

	@RequestMapping(value={"/favorites/songs"}, method = RequestMethod.GET)
	public @ResponseBody Set<Song> getFavoriteSongs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteSongs();
	}

	@RequestMapping(value={"/favorites/playlists"}, method = RequestMethod.GET)
	public @ResponseBody Set<Playlist> getFavoritePlaylists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoritePlaylists();
	}

	@RequestMapping(value={"/favorites/artists"}, method = RequestMethod.GET)
	public @ResponseBody Set<Artist> getFavoriteArtists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteArtists();
	}

	@RequestMapping(value={"/favorites/genres"}, method = RequestMethod.GET)
	public @ResponseBody Set<Genre> getFavoriteGenres(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteGenres();
	}

	@RequestMapping(value={"/favorites/stations"}, method = RequestMethod.GET)
	public @ResponseBody Set<Station> getFavoriteStations(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteStations();
	}

	@RequestMapping(value={"/favorites/add/genre/{genreId}"})
	public @ResponseBody boolean addFavoriteGenre(HttpSession session, @PathVariable Integer genreId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteGenre(user, genreId);
	}

	@RequestMapping(value={"/favorites/remove/genre/{genreId}"})
	public @ResponseBody boolean removeFavoriteGenre(HttpSession session, @PathVariable Integer genreId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteGenre(user, genreId);
	}

	@RequestMapping(value={"/favorites/add/song/{songId}"})
	public @ResponseBody boolean addFavoriteSong(HttpSession session, @PathVariable Integer songId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteSong(user, songId);
	}

	@RequestMapping(value={"/favorites/remove/song/{songId}"})
	public @ResponseBody boolean removeFavoriteSong(HttpSession session, @PathVariable Integer songId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteSong(user, songId);
	}

	@RequestMapping(value={"/favorites/add/album/{albumId}"})
	public @ResponseBody boolean addFavoriteAlbum(HttpSession session, @PathVariable Integer albumId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteAlbum(user, albumId);
	}

	@RequestMapping(value={"/favorites/remove/album/{albumId}"})
	public @ResponseBody boolean removeFavoriteAlbum(HttpSession session, @PathVariable Integer albumId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteAlbum(user, albumId);
	}

	@RequestMapping(value={"/favorites/add/artist/{artistId}"})
	public @ResponseBody boolean addFavoriteArtist(HttpSession session, @PathVariable Integer artistId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteArtist(user, artistId);
	}

	@RequestMapping(value={"/favorites/remove/artist/{artistId}"})
	public @ResponseBody boolean removeFavoriteArtist(HttpSession session, @PathVariable Integer artistId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteArtist(user, artistId);
	}

	@RequestMapping(value={"/favorites/add/playlist/{playlistId}"})
	public @ResponseBody boolean addFavoritePlaylist(HttpSession session, @PathVariable Integer playlistId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoritePlaylist(user, playlistId);
	}

	@RequestMapping(value={"/favorites/remove/playlist/{playlistId}"})
	public @ResponseBody boolean removeFavoritePlaylist(HttpSession session, @PathVariable Integer playlistId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoritePlaylist(user, playlistId);
	}

	@RequestMapping(value={"/followUser/{userId}"})
	public @ResponseBody boolean addFollowedUser(HttpSession session, @PathVariable Integer userId) {
		User user = (User) session.getAttribute("user");
		return userService.addFollowedUser(user, userId);
	}

	@RequestMapping(value={"/unfollowUser/{userId}"})
	public @ResponseBody boolean unfollowedUser(HttpSession session, @PathVariable Integer userId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFollowedUser(user, userId);
	}
