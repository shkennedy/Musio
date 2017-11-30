package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Audio;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.repo.FileRepository;
import com.sbu.webspotify.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/resource")
public class ResourceController
{
    @Autowired
    FileRepository fileRepository;

    @Autowired
    SongService songService;

    @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getResource(@PathVariable("fileId") int fileId) {
        ApiResponseObject response = new ApiResponseObject();
        File file = fileRepository.findById(fileId);
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("No file found with id "+fileId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }

    @RequestMapping(value = "/highBitrateSongFile/{songId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getHighBitrateSong(@PathVariable("songId") int songId) {
        ApiResponseObject response = new ApiResponseObject();
        Song song = songService.getSongById(songId);
        if(song == null) {
            response.setSuccess(false);
            response.setMessage("No song found with id "+songId+".");
            return response;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve high bitrate data for song with id "+songId+".");
            return response;
        }

        File file = fileRepository.findById(audio.getHighBitrateFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve high bitrate data for song with id "+songId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }

    @RequestMapping(value = "/lowBitrateSongFile/{songId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getLowBitrateSong(@PathVariable("songId") int songId) {
        ApiResponseObject response = new ApiResponseObject();
        Song song = songService.getSongById(songId);
        if(song == null) {
            response.setSuccess(false);
            response.setMessage("No song found with id "+songId+".");
            return response;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve low bitrate data for song with id "+songId+".");
            return response;
        }

        File file = fileRepository.findById(audio.getLowBitrateFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve low bitrate data for song with id "+songId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }
}