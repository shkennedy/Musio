package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.repo.AlbumRepository;
import com.sbu.webspotify.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/album")
public class AlbumController
{
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getAlbum(@PathVariable("id") int id) {
        Album album = albumService.getAlbumById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(album == null){
            response.setSuccess(false);
            response.setMessage("No album found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(album);
        }
        return response;
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject addAlbum(@RequestBody Album album) {
        Album newAlbum = albumRepository.save(album);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(newAlbum != null);
        response.setResponseData(newAlbum);
        return response;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject updateAlbum(@RequestBody Album album) {
		Album newAlbum = albumRepository.save(album);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(newAlbum != null);
        response.setResponseData(newAlbum);
        return response;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject deleteAlbum(@PathVariable("id") int id) {
        albumRepository.delete(id);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(true);
        return response;
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Album> getAllAlbums()
    {
        return albumRepository.findAll();
    }

}