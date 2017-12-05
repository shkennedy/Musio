package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.Audio;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.Image;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.repo.FileRepository;
import com.sbu.webspotify.service.AlbumService;
import com.sbu.webspotify.service.ArtistService;
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

    @Autowired
    AlbumService albumService;

    @Autowired
    ArtistService artistService;

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
    public @ResponseBody byte[] getHighBitrateSong(@PathVariable("songId") int songId) {
        ApiResponseObject response = new ApiResponseObject();
        Song song = songService.getSongById(songId);
        if(song == null) {
            response.setSuccess(false);
            response.setMessage("No song found with id "+songId+".");
            return null;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve high bitrate data for song with id "+songId+".");
            return null;
        }

        File file = fileRepository.findById(audio.getHighBitrateFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve high bitrate data for song with id "+songId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/lowBitrateSongFile/{songId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getLowBitrateSong(@PathVariable("songId") int songId) {
        ApiResponseObject response = new ApiResponseObject();
        Song song = songService.getSongById(songId);
        if(song == null) {
            response.setSuccess(false);
            response.setMessage("No song found with id "+songId+".");
            return null;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve low bitrate data for song with id "+songId+".");
            return null;
        }

        File file = fileRepository.findById(audio.getLowBitrateFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve low bitrate data for song with id "+songId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/albumArtworkFullFile/{albumId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getAlbumArtwork(@PathVariable("albumId") int albumId) {
        ApiResponseObject response = new ApiResponseObject();
        Album album = albumService.getAlbumById(albumId);
        if(album == null) {
            response.setSuccess(false);
            response.setMessage("No album found with id "+albumId+".");
            return response;
        }

        Image image = album.getAlbumArt();
        if(image == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve album art data for album with id "+albumId+".");
            return response;
        }

        File file = fileRepository.findById(image.getFullFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve album art data for album with id "+albumId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }

    @RequestMapping(value = "/albumArtworkThumbnailFile/{albumId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getAlbumArtworkThumbnail(@PathVariable("albumId") int albumId) {
        ApiResponseObject response = new ApiResponseObject();
        Album album = albumService.getAlbumById(albumId);
        if(album == null) {
            response.setSuccess(false);
            response.setMessage("No album found with id "+albumId+".");
            return response;
        }

        Image image = album.getAlbumArt();
        if(image == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve album art data for album with id "+albumId+".");
            return response;
        }

        File file = fileRepository.findById(image.getThumbFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve album art data for album with id "+albumId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }

    @RequestMapping(value = "/artistImageFullFile/{artistId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getArtistFullArtwork(@PathVariable("artistId") int artistId) {
        ApiResponseObject response = new ApiResponseObject();
        Artist artist = artistService.getArtistById(artistId);
        if(artist == null) {
            response.setSuccess(false);
            response.setMessage("No artist found with id "+artistId+".");
            return response;
        }

        Image image = artist.getArtistImage();
        if(image == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve artist image data for artist with id "+artistId+".");
            return response;
        }

        File file = fileRepository.findById(image.getFullFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve artist image data for artist with id "+artistId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }

    @RequestMapping(value = "/artistImageThumbnailFile/{artistId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getArtistThumbnailArtwork(@PathVariable("artistId") int artistId) {
        ApiResponseObject response = new ApiResponseObject();
        Artist artist = artistService.getArtistById(artistId);
        if(artist == null) {
            response.setSuccess(false);
            response.setMessage("No artist found with id "+artistId+".");
            return response;
        }

        Image image = artist.getArtistImage();
        if(image == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve artist image data for artist with id "+artistId+".");
            return response;
        }

        File file = fileRepository.findById(image.getThumbFileId());
        if(file == null) {
            response.setSuccess(false);
            response.setMessage("Could not retrieve artist image data for artist with id "+artistId+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(file);
        }
        return response;
    }
}