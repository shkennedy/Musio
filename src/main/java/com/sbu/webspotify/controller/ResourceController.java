package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.repo.FileRepository;

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
}