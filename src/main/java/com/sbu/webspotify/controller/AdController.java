package com.sbu.webspotify.controller;

import java.io.IOException;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Advertisement;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.MimeType;
import com.sbu.webspotify.repo.AdRepository;
import com.sbu.webspotify.repo.MimeTypeRepository;
import com.sbu.webspotify.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/advertisement")
public class AdController
{
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private MimeTypeRepository mimeTypeRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(value = "/getRandom", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getRandomAd() {
        ApiResponseObject response = new ApiResponseObject();

        Advertisement ad = adRepository.findRandomAd();
        response.setResponseData(ad);
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/deleteById")
    public @ResponseBody ApiResponseObject getRandomAd(@RequestParam("advertisementId") int advertisementId) {
        ApiResponseObject response = new ApiResponseObject();

        boolean adExists = adRepository.exists(advertisementId);
        if(adExists == false) {
            response.setMessage("No advertisement found with id "+advertisementId+".");
            response.setSuccess(false);
            return response;
        }

        adRepository.delete(advertisementId);

        response.setMessage("Advertisement "+advertisementId+" successfully deleted.");
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/uploadAd", method=RequestMethod.POST)
    public @ResponseBody ApiResponseObject uploadNewAd(@RequestParam("adFile") MultipartFile adFile,
                                                        @RequestParam("desc") String desc) {
        ApiResponseObject response = new ApiResponseObject();

        try{

            MimeType mimeType = mimeTypeRepository.findBySubtype(appConfig.png);
            File advertFile = fileService.uploadFile(adFile.getBytes(), mimeType);
            Advertisement newAd = new Advertisement();
            newAd.setFile(advertFile.getId());
            newAd.setDesription(desc);
            adRepository.save(newAd);
            adRepository.flush();
            
            response.setSuccess(true);

        } catch(IOException e) {
            response.setSuccess(false);
        }

        return response;
    }

    @GetMapping(path="/all")
    public @ResponseBody ApiResponseObject getAllAds()
    {
        return new ApiResponseObject(true, null, adRepository.findAll());
    }

}