package com.sbu.webspotify.service;

import java.util.Set;

import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchService")
public class SearchService {	

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    ArtistService artistService;

    public SearchResults executeSearch(String query) {

        Set<ArtistIdentifier> artists = artistService.searchByName(query);
        Set<PlaylistIdentifier> playlists = playlistService.searchByName(query);
        Set<AlbumIdentifier> albums = albumService.searchByTitle(query);
        Set<SongIdentifier> songs = songService.searchByTitle(query);
        
        SearchResults searchResults = new SearchResults();
        searchResults.setArtists(artists);
        searchResults.setPlaylists(playlists);
        searchResults.setAlbums(albums);
        searchResults.setSongs(songs);

        return searchResults;
    }

}