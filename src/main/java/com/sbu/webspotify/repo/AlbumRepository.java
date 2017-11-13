package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbu.webspotify.model.*;

// Create, Read, Update, Delete -- CRUD
public interface AlbumRepository extends JpaRepository<Album, Integer>
{
    Album findByTitle(String title);
    Album findById(int id);
}