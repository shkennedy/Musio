package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Advertisement;
import com.sbu.webspotify.repo.AdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/advertisement")
public class AdController
{
    @Autowired
    private AdRepository adRepository;

    @RequestMapping(value = "/getRandom", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getRandomAd() {
        ApiResponseObject response = new ApiResponseObject();

        Advertisement ad = adRepository.findRandomAd();
        response.setResponseData(ad);
        response.setSuccess(true);

        return response;
    }

}