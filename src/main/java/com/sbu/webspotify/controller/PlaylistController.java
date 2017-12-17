package com.sbu.webspotify.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.MimeType;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.MimeTypeRepository;
import com.sbu.webspotify.repo.PlaylistRepository;
import com.sbu.webspotify.service.FileService;
import com.sbu.webspotify.service.PlaylistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/playlist") // This means URL's start with /example (after Application path)
public class PlaylistController
{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MimeTypeRepository mimeTypeRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getPlaylist(@PathVariable("id") int id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(playlist == null){
            response.setSuccess(false);
            response.setMessage("No playlist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(playlist);
        }
        return response;
    }

	@RequestMapping(value = "/create", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject addPlaylist(HttpSession session, @RequestParam("playlistName") String playlistName) {
        User user = (User) session.getAttribute("user");
        Playlist newPlaylist = playlistService.createPlaylist(user, playlistName);
        ApiResponseObject response = new ApiResponseObject();
        if (newPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(newPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to create new playlist.");
        }
        return response;
    }

    @RequestMapping(value = "/get/{id}/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject setPlaylistName(@PathVariable("id") int id, @PathVariable("name") String name) {
        Playlist playlist = playlistService.getPlaylistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(playlist == null){
            response.setSuccess(false);
            response.setMessage("No playlist found with ID "+id+".");
        }
        else {
            playlistService.setPlaylistName(playlist, name);
            response.setSuccess(true);
            response.setResponseData(playlist);
        }
        return response;
    }

    @RequestMapping(value = "/setPrivate/{id}/{isPrivate}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject setPlaylistPrivacy(@PathVariable("id") int id, @PathVariable("isPrivate") boolean isPrivate) {
        Playlist playlist = playlistService.getPlaylistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(playlist == null){
            response.setSuccess(false);
            response.setMessage("No playlist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(playlistService.setPlaylistPrivacy(playlist, isPrivate));
        }
        return response;
    }
    
    @RequestMapping(value = "/setCollaborative/{id}/{isCollaborative}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject setPlaylistCollab(@PathVariable("id") int id, @PathVariable("isCollaborative") boolean isCollaborative) {
        Playlist playlist = playlistService.getPlaylistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(playlist == null){
            response.setSuccess(false);
            response.setMessage("No playlist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(playlistService.setPlaylistCollab(playlist, isCollaborative));
        }
        return response;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject deletePlaylist(@PathVariable("id") int id) {
        // TODO 
        playlistRepository.delete(id);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(true);
        return response;
    }	

    @RequestMapping(value = "/addSong/{playlistId}/{songId}", headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject addSongToPlaylist(@PathVariable("playlistId") int playlistId, @PathVariable("songId") int songId,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(user, playlistId, songId);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(updatedPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to add song to playlist.");
        }
        return response;
    }

    @RequestMapping(value = "/removeSong/{playlistId}/{songId}", headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject removeSongFromPlaylist(@PathVariable("playlistId") int playlistId, @PathVariable("songId") int songId,
                                                                    HttpSession session) {
        User user = (User) session.getAttribute("user");        
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(user, playlistId, songId);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(updatedPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to remove song from playlist.");
        }
        return response;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Playlist> getAllPlaylists()
    {
        return playlistService.getAllPlaylists();
    }


    @RequestMapping(path="/updatePlaylistImage", method=RequestMethod.POST)
    public @ResponseBody ApiResponseObject updatePlaylistImage(@RequestParam("playlistId") int playlistId,
                                                               @RequestParam("playlistArtFile") MultipartFile playlistArtFile){
        ApiResponseObject response = new ApiResponseObject();

        boolean playlistExists = playlistRepository.exists(playlistId);
        if(playlistExists == false) {
            response.setMessage("No playlist found with id "+playlistId+".");
            response.setSuccess(false);
            return response;
        }

        try {
            
            Playlist playlist = playlistRepository.findById(playlistId);
            MimeType mimeType = mimeTypeRepository.findBySubtype(appConfig.png);
            File artFile = fileService.uploadFile(playlistArtFile.getBytes(), mimeType);
            playlist.setImageFile(artFile);
            playlistRepository.save(playlist);
            playlistRepository.flush();
            
            response.setSuccess(true);

        } catch(IOException e) {
            e.printStackTrace();
            response.setSuccess(false);
        }

        return response;
    }

}