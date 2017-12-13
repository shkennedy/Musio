package com.sbu.webspotify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.MimeType;
import com.sbu.webspotify.repo.FileRepository;

@Service("fileService")
public class FileService {

    @Autowired
    FileRepository fileRepository;
	
    public File uploadFile(byte[] contents, MimeType mimeType) {
         File file = new File();
         file.setBytes(contents);
         file.setMimeType(mimeType);

         file = fileRepository.save(file);
         fileRepository.flush();
         return file;
    }
}
