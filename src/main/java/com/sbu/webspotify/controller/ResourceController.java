package com.sbu.webspotify.controller;

import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.Audio;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.Image;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.FileRepository;
import com.sbu.webspotify.service.AlbumService;
import com.sbu.webspotify.service.ArtistService;
import com.sbu.webspotify.service.SongService;
import com.sbu.webspotify.service.UserService;

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

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getResource(@PathVariable("fileId") int fileId) {
        return fileRepository.findById(fileId).getBytes();
    }

    @RequestMapping(value = "/userProfileImage/{userId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getProfileImage(@PathVariable("userId") int userId) {
        User user = userService.findUserById(userId);
        if(user == null) {
            return null;
        }

        File profileImageFile = user.getProfileImage();
        if(profileImageFile == null) {
            return null;
        }

        return profileImageFile.getBytes();
    }

    @RequestMapping(value = "/highBitrateSongFile/{songId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getHighBitrateSong(@PathVariable("songId") int songId) {
        Song song = songService.getSongById(songId);
        if(song == null) {
            return null;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            return null;
        }

        File file = fileRepository.findById(audio.getHighBitrateFileId());
        if(file != null) {
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/lowBitrateSongFile/{songId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getLowBitrateSong(@PathVariable("songId") int songId) {
        Song song = songService.getSongById(songId);
        if(song == null) {
            return null;
        }

        Audio audio = song.getAudio();
        if(audio == null) {
            return null;
        }

        File file = fileRepository.findById(audio.getLowBitrateFileId());
        if(file != null) {
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/albumArtworkFullFile/{albumId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getAlbumArtwork(@PathVariable("albumId") int albumId) {
        Album album = albumService.getAlbumById(albumId);
        if(album == null) {
            return null;
        }

        Image image = album.getAlbumArt();
        if(image == null) {
            return null;
        }

        File file = fileRepository.findById(image.getFullFileId());
        if(file != null) {
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/albumArtworkThumbnailFile/{albumId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getAlbumArtworkThumbnail(@PathVariable("albumId") int albumId) {
        Album album = albumService.getAlbumById(albumId);
        if(album == null) {
            return null;
        }

        Image image = album.getAlbumArt();
        if(image == null) {
            return null;
        }

        File file = fileRepository.findById(image.getThumbFileId());
        if (file != null) {
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/artistImageFullFile/{artistId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getArtistFullArtwork(@PathVariable("artistId") int artistId) {
        Artist artist = artistService.getArtistById(artistId);
        if(artist == null) {
            return null;
        }

        Image image = artist.getArtistImage();
        if(image == null) {
            return null;
        }

        File file = fileRepository.findById(image.getFullFileId());
        if (file != null) {
            return file.getBytes();
        }
        return null;
    }

    @RequestMapping(value = "/artistImageThumbnailFile/{artistId}", method = RequestMethod.GET)
    public @ResponseBody byte[] getArtistThumbnailArtwork(@PathVariable("artistId") int artistId) {
        Artist artist = artistService.getArtistById(artistId);
        if(artist == null) {
            return null;
        }

        Image image = artist.getArtistImage();
        if(image == null) {
            return null;
        }

        File file = fileRepository.findById(image.getThumbFileId());
        if (file != null) {
            return file.getBytes();
        }
        return null;
    }
}