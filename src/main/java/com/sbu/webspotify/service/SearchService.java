package com.sbu.webspotify.service;

import java.util.HashSet;
import java.util.Set;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.BrowseResults;
import com.sbu.webspotify.dto.BrowseResults.BrowseResult;
import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.dto.identifier.UserIdentifier;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.AlbumRepository;
import com.sbu.webspotify.repo.GenreRepository;
import com.sbu.webspotify.repo.InstrumentRepository;
import com.sbu.webspotify.repo.SongRepository;

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
    InstrumentRepository instrumentRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    GenreService genreService;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    public SearchResults executeSearch(String query) {

        if (query.length() == 0) {
            return new SearchResults();
        }

        Set<ArtistIdentifier> artists = artistService.searchByName(query);
        Set<PlaylistIdentifier> playlists = playlistService.searchByName(query);
        Set<AlbumIdentifier> albums = albumService.searchByTitle(query);
        Set<SongIdentifier> songs = songService.searchByTitle(query);
        Set<InstrumentIdentifier> instruments = instrumentService.searchByName(query);
        Set<GenreIdentifier> genres = genreService.searchByName(query);
        Set<UserIdentifier> users = userService.searchByUsername(query);
        
        SearchResults searchResults = new SearchResults();
        searchResults.setArtists(artists);
        searchResults.setPlaylists(playlists);
        searchResults.setAlbums(albums);
        searchResults.setSongs(songs);
        searchResults.setInstruments(instruments);
        searchResults.setGenres(genres);
        searchResults.setUsers(users);

        return searchResults;
    }

	public BrowseResults getBrowseContent(User user) {

        BrowseResults browseResults = new BrowseResults();
        
        browseResults.setNewReleases(getNewReleases());
        browseResults.setFriendsFavorites(getFriendsFavorites(user));
        browseResults.setDiscover(getDiscover(user));
        browseResults.setPopular(getPopular());

		return browseResults;
    }
    
    private BrowseResult getNewReleases() {
        Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
        Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
        Set<AlbumIdentifier> albums = albumRepository.findNewReleases(8);
        Set<SongIdentifier> songs = new HashSet<SongIdentifier>();

        return new BrowseResult(songs, artists, albums, playlists);
    }

    private BrowseResult getFriendsFavorites(User user) {
        Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
        Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
        Set<AlbumIdentifier> albums = new HashSet<AlbumIdentifier>();
        Set<SongIdentifier> songs = new HashSet<SongIdentifier>();

        // Go through the user's friends list and get some most recently favorited items.
        for(User friend : user.getFollowedUsers())
        {            
            Set<SongIdentifier> queriedSongs = userService.getMostRecentlyFavoritedSongs(friend.getId(), appConfig.recentFavoritesToQuery);
            for(SongIdentifier song : queriedSongs) {
                if(song.getId() != null) {
                    songs.add(song);
                }
            }

            Set<AlbumIdentifier> queriedAlbums = userService.getMostRecentlyFavoritedAlbums(friend.getId(), appConfig.recentFavoritesToQuery);
            for(AlbumIdentifier album : queriedAlbums) {
                if(album.getId() != null) {
                    albums.add(album);
                }
            }
            
            Set<ArtistIdentifier> queriedArtists = userService.getMostRecentlyFavoritedArtists(friend.getId(), appConfig.recentFavoritesToQuery);
            for(ArtistIdentifier artist : queriedArtists) {
                if(artist.getId() != null) {
                    artists.add(artist);
                }
            }

            Set<PlaylistIdentifier> queriedPlaylists = userService.getMostRecentlyFavoritedPlaylists(friend.getId(), appConfig.recentFavoritesToQuery);
            for(PlaylistIdentifier playlist : queriedPlaylists) {
                if(playlist.getId() != null) {
                    playlists.add(playlist);
                }
            }
        }

        return new BrowseResult(songs, artists, albums, playlists);
    }

    private BrowseResult getDiscover(User user) {
        Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
        Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
        Set<AlbumIdentifier> albums = new HashSet<AlbumIdentifier>();
        Set<SongIdentifier> songs = new HashSet<SongIdentifier>();

        // Get not favorited items from favorited genres
        int nFavoriteArtists = user.getFavoriteArtists().size();
        if (nFavoriteArtists != 0) {
            int nRelatedPerItem =  appConfig.relatedArtistsToQuery / nFavoriteArtists;
            for (Artist favoriteArtist : user.getFavoriteArtists()) {
                for (ArtistIdentifier artist : artistService.getNRelatedArtists(favoriteArtist.getId(), nRelatedPerItem)) {
                    if (artist.getId() != null) {
                        artists.add(artist);
                    }
                }
            }
        } else {
            
        }

        return new BrowseResult(songs, artists, albums, playlists);
    }

    private BrowseResult getPopular() {
        Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
        Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
        Set<AlbumIdentifier> albums = new HashSet<AlbumIdentifier>();
        Set<SongIdentifier> songs = songRepository.findNSongsWithMostListens(20);
        
        return new BrowseResult(songs, artists, albums, playlists);
    }

	public SearchResults searchByInstrument(int instrumentId) {
        SearchResults results = new SearchResults();
        results.setArtists(instrumentRepository.findArtistsByInstrument(instrumentId));
        results.setAlbums(instrumentRepository.findAlbumsByInstrument(instrumentId));
        results.setSongs(instrumentRepository.findSongsByInstrument(instrumentId));
		return results;
	}

	public SearchResults searchByGenre(int genreId) {
		SearchResults results = new SearchResults();
        results.setArtists(genreRepository.findArtistsByGenre(genreId));
        results.setAlbums(genreRepository.findAlbumsByGenre(genreId));
        results.setSongs(genreRepository.findSongsByGenre(genreId));
		return results;
	}
}