package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.domain.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/album")
public class AlbumController
{
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Album getAlbum(@PathVariable("id") int id) {
        return albumRepository.findById(id);
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewAlbum (@RequestParam String title, @RequestParam String artistName)
    {
        Artist artist = artistRepository.findByName(artistName);

        // if artist is null
        if(artist == null) {
            return "Could not find artist in the db.";
        }

        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);

        try
        {
            albumRepository.save(album);
        } catch (Exception e) {
            e.printStackTrace();
            return "Database constraint exception occurred!";
        }

        return "Saved album!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Album> getAllAlbums()
    {
        return albumRepository.findAll();
    }

}