package com.sbu.webspotify.repo;

import org.springframework.data.repository.CrudRepository;
import com.sbu.webspotify.model.*;

// Create, Read, Update, Delete -- CRUD
public interface AlbumRepository extends CrudRepository<Album, Integer>
{
    Album findByTitle(String title);
    Album findById(int id);
}