package com.sbu.webspotify.repo;

import com.sbu.webspotify.model.MimeType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MimeTypeRepository extends JpaRepository<MimeType, Integer>
{
    MimeType findById(int id);
    MimeType findBySubtype(String subtype);
}