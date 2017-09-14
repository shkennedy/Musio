package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.domain.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/artist") // This means URL's start with /example (after Application path)
public class ArtistController
{
    @Autowired
    private ArtistRepository artistRepository;


    @GetMapping(path="/add")
    public @ResponseBody String addNewArtist (@RequestParam String name)
    {
        Artist a = new Artist();
        a.name = name;

        try
        {
            artistRepository.save(a);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Database constraint exception occurred!";
        }

        return "Saved artist '${name}'!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Artist> getAllArtists()
    {
        return artistRepository.findAll();
    }

}