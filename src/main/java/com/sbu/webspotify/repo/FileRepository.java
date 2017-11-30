package com.sbu.webspotify.repo;

import com.sbu.webspotify.model.File;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer>
{
    File findById(int id);
}