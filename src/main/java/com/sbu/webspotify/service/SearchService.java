package com.sbu.webspotify.service;

import java.util.HashSet;
import java.util.Set;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.BrowseResults;
import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchService")
public class SearchService {	

    @Autowired
    AppConfig appConfig;

    @Autowired
    UserService userService;

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    ArtistService artistService;

    @Autowired
    InstrumentService instrumentService;

    @Autowired
    GenreService genreService;

    public SearchResults executeSearch(String query) {

        if(query.length() == 0) {
            return new SearchResults();
        }

        Set<ArtistIdentifier> artists = artistService.searchByName(query);
        Set<PlaylistIdentifier> playlists = playlistService.searchByName(query);
        Set<AlbumIdentifier> albums = albumService.searchByTitle(query);
        Set<SongIdentifier> songs = songService.searchByTitle(query);
        Set<InstrumentIdentifier> instruments = instrumentService.searchByName(query);
        Set<GenreIdentifier> genres = genreService.searchByName(query);
        
        SearchResults searchResults = new SearchResults();
        searchResults.setArtists(artists);
        searchResults.setPlaylists(playlists);
        searchResults.setAlbums(albums);
        searchResults.setSongs(songs);
        searchResults.setInstruments(instruments);
        searchResults.setGenres(genres);

        return searchResults;
    }

	public BrowseResults getBrowseContent(User user) {

        Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
        Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
        Set<AlbumIdentifier> albums = new HashSet<AlbumIdentifier>();
        Set<SongIdentifier> songs = new HashSet<SongIdentifier>();

        // Go through the user's friends list and get some most recently favorited items.
        for(User friend : user.getFollowedUsers())
        {
            Set<SongIdentifier> queriedSongs = userService.getMostRecentlyFavoritedSongs(friend.getId(), appConfig.recentFavoritesToQuery);
            for(SongIdentifier song : queriedSongs) {
                songs.add(song);
            }

            Set<AlbumIdentifier> queriedAlbums = userService.getMostRecentlyFavoritedAlbums(friend.getId(), appConfig.recentFavoritesToQuery);
            for(AlbumIdentifier album : queriedAlbums) {
                albums.add(album);
            }

            Set<ArtistIdentifier> queriedArtists = userService.getMostRecentlyFavoritedArtists(friend.getId(), appConfig.recentFavoritesToQuery);
            for(ArtistIdentifier artist : queriedArtists) {
                artists.add(artist);
            }

            Set<PlaylistIdentifier> queriedPlaylists = userService.getMostRecentlyFavoritedPlaylists(friend.getId(), appConfig.recentFavoritesToQuery);
            for(PlaylistIdentifier playlist : queriedPlaylists) {
                playlists.add(playlist);
            }
        }

        BrowseResults browseResults = new BrowseResults();
        browseResults.setArtists(artists);
        browseResults.setPlaylists(playlists);
        browseResults.setAlbums(albums);
        browseResults.setSongs(songs);

		return browseResults;
	}

}