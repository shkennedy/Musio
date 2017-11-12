package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/song")
public class SongController
{
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewSong (@RequestParam String title, @RequestParam String album)
    {
        Album alb = albumRepository.findByTitle(album);
        if(alb == null)
        {
            return "Album not found in db.";
        }

        Song s = new Song();
        s.setTitle(title);
        try {
            songRepository.save(s);
        } catch (Exception e) {
            e.printStackTrace();
            return "Database constraint exception occurred!";
        }

        return "Successfully saved song!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Song> getAllSongs()
    {
        return songRepository.findAll();
    }

}