package com.sbu.webspotify.controller

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.*

import com.sbu.webspotify.domain.*
import com.sbu.webspotify.repo.*

@Controller
@RequestMapping(path = '/album')
public class AlbumController
{
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewAlbum (@RequestParam String title, @RequestParam String artist)
    {
        Artist art = artistRepository.findByName(artist)

        // if artist is null
        if(!art) {
            return "Could not find artist '${artist}' in the db."
        }

        Album a = new Album()
        a.title = title
        a.artist = art

        try
        {
            albumRepository.save(a)
        } catch (Exception e) {
            e.printStackTrace()
            return "Database constraint exception occurred!"
        }

        return "Saved album '${title}' by artist '${artist}'!"
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Album> getAllAlbums()
    {
        return albumRepository.findAll();
    }

}