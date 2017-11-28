package com.sbu.webspotify.service;

import java.util.Set;

import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
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

}